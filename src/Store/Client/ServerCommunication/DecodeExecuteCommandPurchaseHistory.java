package Store.Client.ServerCommunication;

import java.sql.SQLException;

import Store.Database.PurchaseHistoryDAO;
import Store.PurchaseHistory.Purchase;

public class DecodeExecuteCommandPurchaseHistory {
    public static String execute(String command) throws SQLException
    {
        PurchaseHistoryDAO DAO = new PurchaseHistoryDAO();
        Purchase purchase;
        String response = Format.encodeSuccessMessage();

        switch(Format.getMethod(command)) {

            case "createNewPurchase":
                purchase = Purchase.deserializeFromString(Format.getFirstParam(command), 1);
                DAO.createNewPurchase(purchase);
                break;
            case "getItemsFromOrdersByBranchAndDays":
                String branch = Format.getFirstParam(command);
                int days = Integer.parseInt(Format.getSecondParam(command));
                DAO.getItemsFromOrdersByBranchAndDays(branch, days);
                break;
        }
        return response;
    }
}
