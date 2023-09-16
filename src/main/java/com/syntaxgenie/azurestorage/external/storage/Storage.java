package com.syntaxgenie.azurestorage.external.storage;

import java.io.InputStream;

public interface Storage {
    /**
     * Upload data with given file name to the storage location.
     *
     * @param data        Data to be uploaded
     * @param storagePath Location of the data to be saved
     * @param fileName    File name
     * @return String URL to the uploaded data
     */
    String upload(InputStream data, StoragePath storagePath, String fileName);

    /**
     * Read data for given file name from the storage location.
     *
     * @param storagePath Location of the data to be read
     * @param fileName    Name of the file to be read
     * @return OutputStream Data stream
     */
    InputStream read(StoragePath storagePath, String fileName);
}
