package project.houserent;

import project.houserent.view.HouseView;

public class HomeRentApp {
    public static void main(String[] args) {
        //创建HouseView，并显示主菜单，是整个程序的入口
        new HouseView().mainMenu();
        System.out.println("---------你退出了房屋出租系统--------");
    }
}
