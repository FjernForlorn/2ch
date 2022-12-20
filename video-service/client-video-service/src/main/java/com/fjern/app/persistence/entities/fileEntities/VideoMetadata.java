package com.fjern.app.persistence.entities.fileEntities;

import com.fjern.common.interfaces.NameableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "video_metadata")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoMetadata implements NameableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String contentType;

    @Column
    private String description;

    @Column
    private Long fileSize;

    @Column
    private Long videoLength;

}
