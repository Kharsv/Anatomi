package com.kharsh;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {

    private int columnCount = 3;
    private ArrayList<String[]> tableData;

    public TableModel(){
        tableData = new ArrayList<>();
 //       for (int i=0;i<tableData.size();++i){
 //           tableData.add(new String[6]);
 //       }
    }

    public void addData(String[] row){
 //       String []rowTable;
 //       rowTable = row;
        tableData.add(row);
    }

    @Override
    public int getRowCount() {
        return tableData.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableData.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0: return "Фамилия";
            case 1: return "Имя";
            case 2: return "Отчество";
   //         case 3: return "имейл";
   //         case 4: return "Телефон";
        }
        return "";
    }

    public void clear(){
        tableData.clear();
    }

}
