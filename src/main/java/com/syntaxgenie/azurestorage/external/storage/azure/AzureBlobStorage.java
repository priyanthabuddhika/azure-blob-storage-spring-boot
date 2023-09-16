package com.syntaxgenie.azurestorage.external.storage.azure;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.syntaxgenie.azurestorage.external.storage.Storage;
import com.syntaxgenie.azurestorage.external.storage.StoragePath;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
class AzureBlobStorage implements Storage {
    private final BlobContainerClient blobContainerClient;

    public AzureBlobStorage(AzureBlobStorageProperties blobStorageProperties) {
        this.blobContainerClient = new BlobContainerClientBuilder()
                .endpoint(blobStorageProperties.getEndpoint())
                .sasToken(blobStorageProperties.getSasToken())
                .containerName(blobStorageProperties.getContainerName())
                .buildClient();
    }

    @Override
    public String upload(InputStream data, StoragePath storagePath, String fileName) {
        getBlobClient(storagePath, fileName).upload(data, true);
        return generateResourceUrl(storagePath, fileName);
    }

    @Override
    public InputStream read(StoragePath storagePath, String fileName) {
        return getBlobClient(storagePath, fileName).openInputStream();
    }

    private BlobClient getBlobClient(final StoragePath storagePath, final String blobName) {
        return blobContainerClient.getBlobClient(storagePath.getValue() + blobName);
    }

    private String generateResourceUrl(final StoragePath storagePath, final String blobName) {
        return blobContainerClient.getBlobContainerUrl() + "/" + storagePath.getValue() + blobName;
    }
}
