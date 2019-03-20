package me.glagolev.baumaneateries.core.repo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Базовый класс для хранение и предоставление данных не зависящий от жизненного цикла
 * ViewController'ов. Содержит метод readJsonFile для представления json-файла в виде строки
 */
public abstract class BaseRepository {

    protected String readJsonFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte bufferByte[] = new byte[1024];
        int length;
        try {
            while ((length = inputStream.read(bufferByte)) != -1) {
                outputStream.write(bufferByte, 0, length);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }
}
