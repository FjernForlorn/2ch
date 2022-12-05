package com.fjern.app.persistence.repositories;

import com.fjern.common.interfaces.ByNameApi;
import com.fjern.app.persistence.entities.fileEntities.VideoMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoMetadataRepository extends JpaRepository<VideoMetadata, Long>, ByNameApi<VideoMetadata> {
}
