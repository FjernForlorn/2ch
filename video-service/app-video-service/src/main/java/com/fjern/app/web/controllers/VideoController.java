package com.fjern.app.web.controllers;

import com.fjern.app.web.mappers.VideoMapper;
import com.fjern.common.exception.MyResourceNotFoundException;
import com.fjern.app.persistence.entities.DTOs.NewVideoDto;
import com.fjern.app.persistence.entities.DTOs.VideoMetadataDto;
import com.fjern.app.persistence.entities.fileEntities.StreamBytesInfo;
import com.fjern.app.persistence.entities.fileEntities.VideoDao;
import com.fjern.app.persistence.entities.fileEntities.VideoMetadata;
import com.fjern.common.web.controllers.AbstractController;
import com.fjern.common.services.RawService;
import com.fjern.app.services.VideoFileService;
import com.fjern.app.services.VideoMetadataService;
import com.fjern.app.services.VideoPreviewImageFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "${api.video.address}")
@Slf4j
public class VideoController extends AbstractController<VideoMetadata> {

    @Autowired
    private VideoPreviewImageFileService videoPreviewImageFileService;
    @Autowired
    private VideoFileService videoFileService;
    @Autowired
    private VideoMetadataService videoMetadataService;
    @Autowired
    private VideoMapper mapper;

    public VideoController() {
        super(VideoMetadata.class);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public VideoMetadataDto findOne(@PathVariable Long id, HttpServletResponse response, UriComponentsBuilder uriBuilder){
        return mapper.videoMetadataToDto(findOneInternal(id, response, uriBuilder));
    }

    @GetMapping("/all")
    public List<VideoMetadataDto> findAll(final HttpServletRequest httpServletRequest) {
        return findAllInternal(httpServletRequest).stream().map(v -> mapper.videoMetadataToDto(v)).collect(Collectors.toList());
    }

    @GetMapping(value = "/preview/{id}", produces =MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<StreamingResponseBody> getPreviewPicture(@PathVariable Long id
            , HttpServletResponse response, UriComponentsBuilder uriBuilder) {

        VideoMetadata videoMetadata = findOneInternal(id, response, uriBuilder);
        InputStream inputStream = videoPreviewImageFileService.getImageInputStream(videoMetadata).orElseThrow(MyResourceNotFoundException::new);

        return ResponseEntity.ok(new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                inputStream.transferTo(outputStream);
            }
        });
    }

    @GetMapping(value = "/stream/{id}", produces =MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<StreamingResponseBody> streamVideo(
            @RequestHeader(value = "Range", required = false) String httpRangeHeader,
            @PathVariable Long id, HttpServletResponse response, UriComponentsBuilder uriBuilder ){
        List<HttpRange> httpRangeList = HttpRange.parseRanges(httpRangeHeader);
        VideoMetadata videoMetadata = findOneInternal(id, response, uriBuilder);
        StreamBytesInfo streamBytesInfo = videoFileService.getStreamBytes(videoMetadata, httpRangeList.size() > 0 ? httpRangeList.get(0) : null).
                orElseThrow(MyResourceNotFoundException::new);
        long byteLength = streamBytesInfo.getRangeEnd() - streamBytesInfo.getRangeStart() + 1;
        ResponseEntity.BodyBuilder builder = ResponseEntity
                .status(httpRangeList.size() > 0 ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK)
                .header("Content-Type", streamBytesInfo.getContentType())
                .header("Accept-Ranges", "bytes")
                .header("Content-Length", Long.toString(byteLength));
        if(httpRangeList.size() > 0) {
            builder.header("Content-Range",
                    "bytes " + streamBytesInfo.getRangeStart() +
                            "-" + streamBytesInfo.getRangeEnd() +
                            "/" + streamBytesInfo.getFileSize());
        }
        log.info("Providing bytes from {} to {}. We are at {}% of overall video", streamBytesInfo.getRangeStart(), streamBytesInfo.getRangeEnd()
                ,new DecimalFormat("###.##").format(100.0 * streamBytesInfo.getRangeStart() / streamBytesInfo.getRangeEnd()));

        return builder.body(streamBytesInfo.getStreamingResponseBody());
    }
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadVideo(NewVideoDto newVideoDto, final UriComponentsBuilder uriBuilder, HttpServletResponse response){
        VideoMetadata videoMetadata = mapper.newVideoDtoToMetadata(newVideoDto);
        createInternal(videoMetadata, uriBuilder, response);
        VideoDao videoDao = mapper.newVideoDtoToVideoDao(newVideoDto);
        videoDao.setId(videoMetadata.getId());
        videoFileService.save(videoDao);
        videoMetadata.setVideoLength(videoDao.getLength());
        updateInternal(videoMetadata.getId(), videoMetadata);
    }

    @Override
    protected RawService<VideoMetadata> getService() {
        return videoMetadataService;
    }
}
