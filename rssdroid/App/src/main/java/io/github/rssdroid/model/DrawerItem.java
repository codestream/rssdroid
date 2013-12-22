package io.github.rssdroid.model;

public final class DrawerItem {
    private String title;
    private int icon;
    private String count = "0";
    private boolean isCounterVisible = false;

    public DrawerItem(){
        //do nothing
    }

    public DrawerItem(String title, int icon){
        this.title = title;
        this.icon = icon;
    }

    public DrawerItem(String title){
        this.title = title;
    }

    public DrawerItem(String title, int icon, boolean isCounterVisible, String count){
        this.title = title;
        this.icon = icon;
        this.isCounterVisible = isCounterVisible;
        this.count = count;
    }

    public DrawerItem(String title, boolean isCounterVisible, String count){
        this.title = title;
        this.isCounterVisible = isCounterVisible;
        this.count = count;
    }

    public String getTitle(){
        return this.title;
    }

    public int getIcon(){
        return this.icon;
    }

    public String getCount(){
        return this.count;
    }

    public boolean getCounterVisibility(){
        return this.isCounterVisible;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setIcon(int icon){
        this.icon = icon;
    }

    public void setCount(String count){
        this.count = count;
    }

    public void setCounterVisibility(boolean isCounterVisible){
        this.isCounterVisible = isCounterVisible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrawerItem that = (DrawerItem) o;

        if (icon != that.icon) return false;
        if (isCounterVisible != that.isCounterVisible) return false;
        if (!count.equals(that.count)) return false;
        if (!title.equals(that.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + icon;
        result = 31 * result + count.hashCode();
        result = 31 * result + (isCounterVisible ? 1 : 0);
        return result;
    }
}
