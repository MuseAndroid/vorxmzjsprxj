package kr.co.genexon.factconnector;

import java.io.File;
import java.io.FilenameFilter;

public class SysUtils {

    private static CertFileFilter mCertFileFilter;

    public static boolean isNull(Object object){

        if(object == null){
            return true;
        }

        if(object instanceof String){
            String str = (String) object;

            if(str.length() < 1 || str.equals("") || str.equals("null")){
                return true;
            }
        }

        return false;
    }


    /**
     * 특정 파일이 특정경로에 존재하는지
     * @param filePath
     * @param fileName
     * @return
     */
    public static String getCertFileFullPath(String filePath, String fileName){
        String result = "";

        try{
            File file = new File(filePath);

            if(mCertFileFilter == null){
                mCertFileFilter = new CertFileFilter();
            }

            mCertFileFilter.setSearchFileName(fileName);

            File dirFiles[] = file.listFiles(mCertFileFilter);

            if(dirFiles != null && dirFiles.length > 0){
                result = dirFiles[0].getAbsolutePath();
            }
        }catch (Exception e){
            e.printStackTrace();
            result = "";
        }

        return result;
    }


    public static class CertFileFilter implements FilenameFilter {

        private String searchFileName = "";

        public void setSearchFileName(String name){
            searchFileName = name;
        }

        @Override
        public boolean accept(File dir, String name)
        {
            boolean isAccept = false;

            if(isNull(searchFileName) == false){
                if(searchFileName.equalsIgnoreCase(name) == true){
                    isAccept = true;
                }else{
                    isAccept = false;
                }
            }

            return isAccept;
        }
    }
}
