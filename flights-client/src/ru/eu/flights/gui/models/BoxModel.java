package ru.eu.flights.gui.models;

import javax.swing.*;
import java.util.List;

public class BoxModel<T> extends AbstractListModel<T> implements ComboBoxModel<T> {

    private List<T> list;
    private T selectedItem;

    public BoxModel(List<T> list) {
        this.list = list;
    }

    @Override
    public void setSelectedItem(Object o) {
        selectedItem = (T) o;
    }

    @Override
    public T getSelectedItem() {
        return selectedItem;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public T getElementAt(int index) {
        return list.get(index);
    }
}
