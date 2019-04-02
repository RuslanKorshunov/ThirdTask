package by.epam.thirdtask.validator;

import java.io.File;

public class FileValidator
{
    public static boolean existFile(String filePath)
    {
        return (new File(filePath).exists());
    }

    public static boolean canReadFile(String filePath)
    {
        return (new File(filePath).canRead());
    }
}
