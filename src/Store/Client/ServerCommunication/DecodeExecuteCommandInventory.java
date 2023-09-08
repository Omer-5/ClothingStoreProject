package Store.Client.ServerCommunication;

import Store.Database.InventoryDAO;
import Store.Inventories.InventoryItem;

public class DecodeExecuteCommandInventory {
    public static String execute(String command)
    {
        InventoryDAO DAO = new InventoryDAO();
        String response = ""; //TODO: add response
        switch (Format.getMethod(command)) {
            case "getInventoryItemsByBranch":
                // public ArrayList<InventoryItem> getInventoryItemsByBranch(String branch) {
                response = Format.encodeInventoryItems(DAO.getInventoryItemsByBranch(Format.getFirstParam(command)));
                break;
            case "createNewItem":
                // public void createNewItem(InventoryItem item) {
                DAO.createNewItem(InventoryItem.deserializeFromString(Format.getFirstParam(command)));
                break;
            case "updateItem":
                // public void updateItem(InventoryItem item) {
                DAO.updateItem(InventoryItem.deserializeFromString(Format.getFirstParam(command)));
                break;
            case "deleteItem":
                // public void deleteItem(int productID) {
                DAO.deleteItem(Integer.parseInt(Format.getFirstParam(command)));
                break;
            case "getItemByProductID":
                // public InventoryItem getItemByProductID(int productID) {
                response = Format.encodeInventoryItems(DAO.getInventoryItemsByBranch(Format.getFirstParam(command)));
                break;
        }
        return response;
    }
}   

