package si.telekom.dis.server;

import java.util.Date;

public interface IBarcodeHandler {
	public String[] getBarcode(int iType, String sCapture, String sCompany, String sLocation, Date dateTime, int nQuantity, String sourceSystem) throws Exception;
	public boolean isBarcodeUsed(String sBarcode, int iTypePar) throws Exception;
	public void overrideConnectionProperties(String barcodeUser, String barcodePassword, String barcodeSqlServerHost, int barcodeSqlServerPort1, String barcodeSqlServerDbName1);
}
