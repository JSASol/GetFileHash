package com.jsa.sample.md5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ファイルのハッシュダイジェストを取得するサンプルプログラム
 */
public class GettingMessageDigestSample {

  public static void main(String[] args) throws FileNotFoundException {
    File file1 = new File("./SampleFiles/test1.txt");
    File file2 = new File("./SampleFiles/test2.txt");
    File file3 = new File("./SampleFiles/test3.txt");
    
    System.out.println("Digest of test1.txt is "+getFileMessageDigest(file1));
    System.out.println("Digest of test2.txt is "+getFileMessageDigest(file2));
    System.out.println("Digest of test3.txt is "+getFileMessageDigest(file3));
  }

  /**
   * ハッシュダイジェストを取得する
   * @param file 入力ファイル
   * @return ハッシュダイジェスト
   */
  private static String getFileMessageDigest(File file){
    // Fileが存在しなければnullを返す
    if(!file.exists()){
      return null;
    }
    
    StringBuilder sb = new StringBuilder();
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      FileInputStream fis = new FileInputStream(file);
      DigestInputStream dis = new DigestInputStream(fis, md);
      
      while (dis.read() != -1) {
        // Nothing to do.
        // (For update digest.)
      }
      
      // バイトを2桁の16進数の文字列に変換し連結する
      byte[] digest = md.digest();
      for (int idx=0;idx<digest.length;++idx) {
        sb.append(String.format("%02x", digest[idx]));
      }
      
      dis.close();
      fis.close();
    } catch (NoSuchAlgorithmException | IOException e) {
      e.printStackTrace();
      return null;
    }
    return sb.toString();
  }
}
