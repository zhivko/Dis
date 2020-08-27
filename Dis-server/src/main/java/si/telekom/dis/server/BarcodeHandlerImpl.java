package si.telekom.dis.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerResultSet;

public class BarcodeHandlerImpl implements IBarcodeHandler {

	public static void setBarcodeUser(String barcodeUser) {
		BarcodeHandlerImpl.barcodeUser = barcodeUser;
	}

	public static void setBarcodePassword(String barcodePassword) {
		BarcodeHandlerImpl.barcodePassword = barcodePassword;
	}

	public static void setBarcodeSqlServerHost(String barcodeSqlServerHost) {
		BarcodeHandlerImpl.barcodeSqlServerHost = barcodeSqlServerHost;
	}

	public static void setBarcodeSqlServerPort(int barcodeSqlServerPort) {
		BarcodeHandlerImpl.barcodeSqlServerPort = barcodeSqlServerPort;
	}

	public static void setBarcodeSqlServerDbName(String barcodeSqlServerDbName) {
		BarcodeHandlerImpl.barcodeSqlServerDbName = barcodeSqlServerDbName;
	}

	public static String barcodeUser;
	public static String barcodePassword;
	public static String barcodeSqlServerHost;
	public static int barcodeSqlServerPort;
	public static String barcodeSqlServerDbName;

	static {
		Properties file = new Properties();
		try {

			// try to get barcode connection parameters from Admin class
			try {
				Class c = Class.forName("si.telekom.dis.server.AdminServiceImpl");
				Logger.getLogger(AdminServiceImpl.class).info(
						"PdfGeneratorImpl class could be loaded - reading barcode properties from class.");
				barcodeUser = c.getField("BARCODE_USER").get(c).toString();
				barcodePassword = c.getField("BARCODE_PASSWORD").get(c).toString();
				barcodeSqlServerHost = c.getField("BARCODE_SQL_SERVER_HOST").get(c).toString();
				barcodeSqlServerPort = Integer.valueOf(c.getField("BARCODE_SQL_SERVER_PORT").get(c).toString()).intValue();
				barcodeSqlServerDbName = c.getField("BARCODE_SQL_SERVER_DB_NAME").get(c).toString();
			} catch (Exception e) {
				Logger.getLogger(BarcodeHandlerImpl.class).info(
						"PdfGeneratorImpl class could not be loaded - reading barcode properties from properties file.");

				// next line requires property antiJARLocking as "true" in the
				// context.xml
				InputStream is = BarcodeHandlerImpl.class.getResourceAsStream("barcode.properties");
				file.load(is);
				barcodeUser = file.getProperty("barcodeUser");
				barcodePassword = file.getProperty("barcodePassword");
				barcodeSqlServerHost = file.getProperty("barcodeSqlServerHost");
				barcodeSqlServerPort = Integer.valueOf(file.getProperty("barcodeSqlServerPort")).intValue();
				barcodeSqlServerDbName = file.getProperty("barcodeSqlServerDbName");
				is.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws Exception {
		SQLServerDataSource ds = null;
		Connection con = null;	
		try {
			ds = new SQLServerDataSource();
			ds.setUser(barcodeUser);
			ds.setPassword(barcodePassword);
			ds.setServerName(barcodeSqlServerHost);
			ds.setPortNumber(barcodeSqlServerPort);
			ds.setDatabaseName(barcodeSqlServerDbName);

			ds.setEncrypt(false);

			ds.setLockTimeout(6000); // set lock timeout to 1000ms
			con = ds.getConnection();
			con.setAutoCommit(false); // this starts first transaction
		} catch (Exception ex) {
			Logger logger = Logger.getLogger(this.getClass().getName());
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			logger.error("Error while calculating barcodes. " + ex.getMessage() + "\n"
					+ errorStringWriter.getBuffer().toString());
			throw ex;
		}

		return con;
	}

	public String[] getBarcode(int iTypePar, String sCapturePar, String sCompanyPar, String sLocationPar,
			Date dateTimePar, int nQuantityPar, String sourceSystem) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> alBarcodes = new ArrayList<String>();
		String ret[] = new String[1];

		Connection con = null;
		try {
			con = getConnection();

			String sDateFormat = "dd.MM.yyyy";
			SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(sDateFormat);
			mySimpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Ljubljana"));

			String sqlQuery = "select * from dbo.T_DOC_ID_STRUCT where iType=" + iTypePar + " and iStatus=1";
			PreparedStatement prep = (PreparedStatement) con.prepareStatement(sqlQuery);
			Logger.getLogger(this.getClass()).debug(sqlQuery);
			SQLServerResultSet rs = (SQLServerResultSet) prep.executeQuery();
			Logger.getLogger(this.getClass()).debug("sqlQuery done.");

			if (rs.next()) {
				String sCapture = rs.getString("sCapture");
				String sCompany = rs.getString("sCompany");
				String sLocation = rs.getString("sLocation");
				String sYear = rs.getString("sYear");
				String sMonth = rs.getString("sMonth");
				String sDay = rs.getString("sDay");
				String sSerial = rs.getString("sSerial");
				int iFree;

				String barcodePrefix = "";

				try {
					int val = Integer.parseInt(sCapture);
					barcodePrefix += sCapture;
				} catch (NumberFormatException ex) {
					barcodePrefix += sCapturePar;
				}

				try {
					int val = Integer.parseInt(sCompany);
					barcodePrefix += sCompany;
				} catch (NumberFormatException ex) {
					barcodePrefix += sCompanyPar;
				}

				try {
					int val = Integer.parseInt(sLocation);
					barcodePrefix += sLocation;
				} catch (NumberFormatException ex) {
					barcodePrefix += sLocationPar;
				}

				String sDateFormat2 = "";
				if (sDay != null && !sDay.equals("")) {
					sDateFormat2 = sYear.replace("Y", "y") + sMonth + sDay.replace("D", "d");
				} else {
					sDateFormat2 = sYear.replace("Y", "y") + sMonth;
				}
				SimpleDateFormat mySimpleDateFormat2 = new SimpleDateFormat(sDateFormat2);
				mySimpleDateFormat2.setTimeZone(TimeZone.getTimeZone("Europe/Ljubljana"));

				String datetimePartOfBarcode = mySimpleDateFormat2.format(dateTimePar);

				barcodePrefix += datetimePartOfBarcode;

				// check if record in T_DOC_ID_FREE exists
				// String sqlQuery2 =
				// "Select * from T_DOC_ID_FREE, T_DOC_ID_STRUCT WITH (TABLOCKX) where T_DOC_ID_FREE.iType = T_DOC_ID_STRUCT.iType and T_DOC_ID_STRUCT.iStatus = 1 and CONVERT(VARCHAR(10), dDate, 104) = '"
				// + mySimpleDateFormat.format(dateTimePar) +
				// "' and  T_DOC_ID_STRUCT.iType = " + iTypePar;
				// prep = (PreparedStatement) con.prepareStatement(sqlQuery2);
				// rs = (SQLServerResultSet) prep.executeQuery();
				//
				// if (rs.next()) {
				// iFree = rs.getInt("iFree");
				// int newFree = iFree + nQuantityPar;
				// prep = (PreparedStatement)
				// con.prepareStatement("update T_DOC_ID_FREE set iFree=" + newFree
				// + " where CONVERT(VARCHAR(10), dDate, 104) = '" +
				// mySimpleDateFormat.format(dateTimePar)
				// + "' and iType = " + iTypePar);
				// prep.execute();
				// } else {
				// int newFree = nQuantityPar + 1; // +1 because we start barcodes with
				// serialNum = 1 !
				// prep = (PreparedStatement) con
				// .prepareStatement("insert into T_DOC_ID_FREE (iType,dDate,iFree) values("
				// + iTypePar
				// + ",convert(datetime, '" + mySimpleDateFormat.format(dateTimePar) +
				// "', 104)," + newFree + ")");
				// prep.execute();
				// iFree = 1; // start new day for this type with barcode serialNum = 1
				// !
				// }

				String sqlQuery2 = "Select * from T_DOC_ID_FREE, T_DOC_ID_STRUCT WITH (TABLOCKX) where T_DOC_ID_FREE.iType = T_DOC_ID_STRUCT.iType and T_DOC_ID_STRUCT.iStatus = 1 and sDate = '"
						+ datetimePartOfBarcode + "' and  T_DOC_ID_STRUCT.iType = " + iTypePar;
				Logger.getLogger(this.getClass()).debug("Executting query: " + sqlQuery2);
				prep = (PreparedStatement) con.prepareStatement(sqlQuery2);
				rs = (SQLServerResultSet) prep.executeQuery();
				Logger.getLogger(this.getClass()).debug("Query done.");

				if (rs.next()) {
					iFree = rs.getInt("iFree");
					int newFree = iFree + nQuantityPar;
					String sql = "update T_DOC_ID_FREE set iFree=" + newFree + ", dDate = convert(datetime, '"
							+ mySimpleDateFormat.format(dateTimePar) + "',104) where sDate = '" + datetimePartOfBarcode
							+ "' and iType = " + iTypePar;
					Logger.getLogger(this.getClass()).debug("Executting query: " + sql);
					prep = (PreparedStatement) con.prepareStatement(sql);
					Logger.getLogger("Executing query done.");
					prep.execute();
				} else {
					int newFree = nQuantityPar + 1; // +1 because we start barcodes with
																					// serialNum = 1 !
					String sql = "insert into T_DOC_ID_FREE (iType,dDate,sDate,iFree) values("
							+ iTypePar + ",convert(datetime, '" + mySimpleDateFormat.format(dateTimePar) + "', 104), '"
							+ datetimePartOfBarcode + "' ," + newFree + ")";
					Logger.getLogger(this.getClass()).debug("Executting query: " + sql);
					prep = (PreparedStatement) con.prepareStatement(sql);
					prep.execute();
					Logger.getLogger(this.getClass()).debug("Executting query done.");
					iFree = 1; // start new day for this type with barcode serialNum = 1 !
				}

				con.commit();

				int barcodeLength = sSerial.length();
				for (int i = 0; i < nQuantityPar; i++) {
					String barcode = barcodePrefix + String.format("%0" + barcodeLength + "d", iFree + i);
					alBarcodes.add(barcode);
				}

				// update log table
				
				String sql = "insert into T_DOC_ID_LOG (iType,sFirst,iCount,dDateInput,sUserInput) values(" + iTypePar
						+ ",'" + alBarcodes.get(0) + "'," + nQuantityPar + ",GETDATE(),'" + sourceSystem + "')";
				Logger logger = Logger.getLogger(this.getClass().getName());
				logger.debug("Sql: " + sql);
				prep = (PreparedStatement) con
						.prepareStatement(sql);
				prep.execute();
				con.commit();

			} else {
				throw new Exception("There is no such iType or it is inactive (iStatus=0)\ncall parameters: iTypePar:"
						+ iTypePar + ", sCapturePar:" + sCapturePar + ", sCompanyPar:" + sCompanyPar + ", sLocationPar:"
						+ sLocationPar + ",\r\n" + "			dateTimePar:" + dateTimePar + ", nQuantityPar:" + nQuantityPar
						+ ", sourceSystem:" + sourceSystem);
			}

		} catch (Exception ex) {
			Logger logger = Logger.getLogger(this.getClass().getName());
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			logger.error("Error while calculating barcodes. " + ex.getMessage() + "\n"
					+ errorStringWriter.getBuffer().toString());
			try {
				if (con != null) {
					con.rollback();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			throw ex;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		ret = alBarcodes.toArray(ret);
		return ret;
	}

	public static void main(String[] args) {
		// test case for new barcode imlpementation
		IBarcodeHandler barcodeHandler = new BarcodeHandlerImpl();
		System.out.println("Creating barcode handler...Created.");

		try {
			String[] barcodes = barcodeHandler.getBarcode(4, "0", "9", "18", new Date(), 100, "test");

			System.out.println("Barcodes:");
			for (String barcode : barcodes) {
				System.out.println(barcode);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean isBarcodeUsed(String sBarcode, int iTypePar) throws Exception {
		// TODO Auto-generated method stub
		boolean ret = false;

		Connection con = null;
		SQLServerResultSet rs = null;
		SQLServerResultSet rs2 = null;
		try {
			con = getConnection();
			Statement stmt = con.createStatement();

			String sDateFormat = "dd.MM.yyyy";
			SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(sDateFormat);
			mySimpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Ljubljana"));

			String sqlQuery = "select * from dbo.T_DOC_ID_STRUCT where iType=" + iTypePar + " and iStatus=1";
			PreparedStatement prep = (PreparedStatement) con.prepareStatement(sqlQuery);
			rs = (SQLServerResultSet) prep.executeQuery();

			if (rs.next()) {
				String sSerial = rs.getString("sSerial");
				int barcodeLength = sSerial.length();

				String sqlQuery2 = "select * from dbo.T_DOC_ID_LOG where left(sFirst," + barcodeLength + ")='"
						+ sBarcode.substring(0, barcodeLength) + "'";
				PreparedStatement prep2 = (PreparedStatement) con.prepareStatement(sqlQuery2);
				rs2 = (SQLServerResultSet) prep2.executeQuery();

				while (rs2.next()) {
					String sFirstBarcode = rs2.getString("sFirst");
					int counterFirstBarcode = Integer.valueOf(sFirstBarcode.substring(sFirstBarcode.length() - barcodeLength))
							.intValue();
					int counterLastBarcode = counterFirstBarcode + rs2.getInt("iCount");
					String formattedCounterLastBarcode = String.format("%0" + barcodeLength + "d", counterLastBarcode);
					String sLastBarcode = sFirstBarcode.substring(0, sFirstBarcode.length() - barcodeLength)
							+ formattedCounterLastBarcode;

					if (sBarcode.compareTo(sFirstBarcode) >= 0 && sBarcode.compareTo(sLastBarcode) < 0) {
						ret = true;
						break;
					}
				}
			} else {
				throw new Exception("There is no such iType or it is inactive (iStatus=0)");
			}
		} catch (Exception ex) {
			try {
				if (con != null) {
					con.rollback();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			throw ex;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (rs != null)
				rs.close();
			if (rs2 != null)
				rs2.close();
		}
		return ret;
	}
	
	public void overrideConnectionProperties(String barcodeUser1, String barcodePassword1, String barcodeSqlServerHost1, int barcodeSqlServerPort1, String barcodeSqlServerDbName1)
	{
		barcodeUser = barcodeUser1;
		barcodePassword = barcodePassword1;
		barcodeSqlServerHost = barcodeSqlServerHost1;
		barcodeSqlServerPort = barcodeSqlServerPort1;
		barcodeSqlServerDbName = barcodeSqlServerDbName1;		
	}
}