package Store.Client.ServerCommunication;

import java.sql.SQLException;
import java.util.List;

import Store.Database.PurchaseHistoryDAO;
import Store.PurchaseHistory.Purchase;
import Store.PurchaseHistory.PurchasedItem;

public class DecodeExecuteCommandPurchaseHistory {
    public static String execute(String command) throws SQLException
    {
        PurchaseHistoryDAO DAO = new PurchaseHistoryDAO();
        Purchase purchase;
        String response = Format.encodeSuccessMessage();

        switch(Format.getMethod(command)) {

            case "createNewPurchase":
                System.out.println(command);
                purchase = Purchase.deserializeFromString(command, 1);
                DAO.createNewPurchase(purchase);
                break;
            case "getItemsFromOrdersByBranchAndDays":
                String branch = Format.getFirstParam(command);
                int days = Integer.parseInt(Format.getSecondParam(command));
                List<PurchasedItem> temp = DAO.getItemsFromOrdersByBranchAndDays(branch, days);
                if(temp.size() == 0)
                    response = Format.encodeException("אין נתונים עבור היום שנבחר");
                else
                    response = Format.encodePurchasedItems(temp);
                break;
        }
        return response;
    }
}
