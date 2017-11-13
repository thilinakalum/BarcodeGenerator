/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.DBConnection;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.BracodePrint;
import model.ImgModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Kalum
 */
public class Controller {

    private Connection connection;

//    public void createBarcode(String barcode) throws IOException, ConfigurationException, BarcodeException {
//        BarcodeUtil util = BarcodeUtil.getInstance();
//        BarcodeGenerator gen = util.createBarcodeGenerator(buildCfg("code39"));
//
//        OutputStream fout = new FileOutputStream("./Barcode/" + barcode + ".jpg");
////        OutputStream fout = new FileOutputStream("D:\\Supervision\\Barcode\\image\\" + barcode + ".jpg");
//        int resolution = 200;
//        BitmapCanvasProvider canvas = new BitmapCanvasProvider(
//                fout, "image/jpeg", resolution, BufferedImage.TYPE_BYTE_BINARY, false, 0);
//
//        gen.generateBarcode(canvas, barcode);
//        canvas.finish();
//    }

//    private Configuration buildCfg(String type) {
//        DefaultConfiguration cfg = new DefaultConfiguration("barcode");
//
//        //Bar code type
//        DefaultConfiguration child = new DefaultConfiguration(type);
//        cfg.addChild(child);
//
//        //Human readable text position
//        DefaultConfiguration attr = new DefaultConfiguration("human-readable");
//        DefaultConfiguration subAttr = new DefaultConfiguration("placement");
//        subAttr.setValue("bottom");
//        attr.addChild(subAttr);
//
//        child.addChild(attr);
//        return cfg;
//    }

    public void deleteOldData() throws SQLException {
        connection = DBConnection.getDBConnection().getConnection();
        String deleteSql = "delete from img";
        PreparedStatement preparedStatementDelete = connection.prepareStatement(deleteSql);
        preparedStatementDelete.executeUpdate();
        preparedStatementDelete.close();
        System.out.println("Delete Old data..");
    }

    public void saveBarcode(ImgModel imgModel) throws SQLException {
        connection = DBConnection.getDBConnection().getConnection();

        deleteOldData();

        String insertSql = "SET IDENTITY_INSERT img OFF; insert into img (description,cost,barcode) values (?,?,?)";
//        File file = new File("./Barcode/" + imgModel.getBarcode() + ".jpg");
//File file = new File("D:\\Supervision\\Barcode\\image\\" + imgModel.getBarcode() + ".jpg");        
PreparedStatement preparedStatementInsert = connection.prepareStatement(insertSql);

        if (imgModel.getPrintQty() % 2 == 0) {
            int no1 = 1;
//            byte[] image1Bite = convertFileToByte(file);
//            byte[] image2Bite = convertFileToByte(file);
            for (int i = 0; i < imgModel.getPrintQty() / 2; i++) {
//                preparedStatementInsert.setBytes(1, image1Bite);
//                preparedStatementInsert.setBytes(2, image2Bite);
                preparedStatementInsert.setString(1, imgModel.getBcdesc());
                preparedStatementInsert.setDouble(2, imgModel.getCost());
                preparedStatementInsert.setString(3, imgModel.getBarcode());
                preparedStatementInsert.executeUpdate();
            }
        } else {
            int val = imgModel.getPrintQty() / 2;
            val++;
//            byte[] image1Bite = convertFileToByte(file);
//            byte[] image2Bite = convertFileToByte(file);
            for (int i = 0; i < val; i++) {
//                preparedStatementInsert.setBytes(1, image1Bite);
//                preparedStatementInsert.setBytes(2, image2Bite);
                preparedStatementInsert.setString(1, imgModel.getBcdesc());
                preparedStatementInsert.setDouble(2, imgModel.getCost());
                preparedStatementInsert.setString(3, imgModel.getBarcode());
                preparedStatementInsert.executeUpdate();
            }
            System.out.println("Save success...");
            preparedStatementInsert.close();

        }
//        PreparedStatement preparedStatementUpdatde = connection.prepareStatement(insertSql);
//        String updateSql = "UPDATE BarcodePrint SET barcodeprint='1' where ""
    }

    public void viewJasperReport() throws JRException, SQLException {
        String reportPath = "report/barcode.jrxml";
//        String reportPath = "D:\\Supervision\\Barcode\\report\\barcode.jrxml";
        JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getDBConnection().getConnection());
//        JasperViewer.setDefaultLookAndFeelDecorated(true);
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
    }

//    public byte[] convertFileToByte(File file) {
//        FileInputStream fis = null;
//        byte[] biteData = new byte[(int) file.length()];
//        try {
//            fis = new FileInputStream(file);
//            fis.read(biteData);
//            fis.close();
//        } catch (FileNotFoundException ex) {
//            System.out.println("My-Error-catch" + ex);
//        } catch (IOException ex) {
//            System.out.println("My-Error-catch" + ex);
//        }
//        return biteData;
//    }

    public void saveAllBarcode(ImgModel imgModel) throws SQLException {
        connection = DBConnection.getDBConnection().getConnection();
        String insertSql = "SET IDENTITY_INSERT img OFF; insert into img (description,cost,barcode) values (?,?,?)";
//        File file = new File("./Barcode/" + imgModel.getBarcode() + ".jpg");
//        File file = new File("D:\\Supervision\\Barcode\\image\\" + imgModel.getBarcode() + ".jpg");
        PreparedStatement preparedStatementInsert = connection.prepareStatement(insertSql);

        if (imgModel.getPrintQty() % 2 == 0) {
//            byte[] image1Bite = convertFileToByte(file);
//            byte[] image2Bite = convertFileToByte(file);
            for (int i = 0; i < imgModel.getPrintQty() / 2; i++) {
//                preparedStatementInsert.setBytes(1, image1Bite);
//                preparedStatementInsert.setBytes(2, image2Bite);
                 preparedStatementInsert.setString(1, imgModel.getBcdesc());
                preparedStatementInsert.setDouble(2, imgModel.getCost());
                preparedStatementInsert.setString(3, imgModel.getBarcode());
                preparedStatementInsert.executeUpdate();
            }
        } else {
            int val = imgModel.getPrintQty() / 2;
            val++;
//            byte[] image1Bite = convertFileToByte(file);
//            byte[] image2Bite = convertFileToByte(file);
            for (int i = 0; i < val; i++) {
//                preparedStatementInsert.setBytes(1, image1Bite);
//                preparedStatementInsert.setBytes(2, image2Bite);
                 preparedStatementInsert.setString(1, imgModel.getBcdesc());
                preparedStatementInsert.setDouble(2, imgModel.getCost());
                preparedStatementInsert.setString(3, imgModel.getBarcode());
                preparedStatementInsert.executeUpdate();
            }
            System.out.println("Save success...");
            preparedStatementInsert.close();

        }
    }

    public ArrayList<BracodePrint> getAllBarcodeDataByGrnRefNo(String grnrefNo) throws SQLException {
        connection = DBConnection.getDBConnection().getConnection();
        String sql = "select * from BarcodePrint where RECNO='" + grnrefNo + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<BracodePrint> barprintProductList = new ArrayList<BracodePrint>();
        while (resultSet.next()) {
            BracodePrint bracodePrint = new BracodePrint();
            bracodePrint.setRecNo(resultSet.getString(1));
            bracodePrint.setBarcodePrint(resultSet.getInt(2));
            bracodePrint.setProNo(resultSet.getString(3));
            bracodePrint.setBcQty(resultSet.getInt(4));
            bracodePrint.setBarCode(resultSet.getString(5));
            bracodePrint.setBcDes(resultSet.getString(6));
            bracodePrint.setPrice(resultSet.getFloat(7));
            barprintProductList.add(bracodePrint);
        }
        return barprintProductList;
    }

}
