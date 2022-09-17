package com.fjern.app.persistence.entities.fileEntities;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RequiredArgsConstructor
@Data
public class StreamBytesInfo {

    private final StreamingResponseBody streamingResponseBody;

    private final long fileSize;

    private final long rangeStart;

    private final long rangeEnd;

    private final String contentType;

}
