package project.houserent.service;

import project.houserent.domain.House;

/**
 * 定义House[]，保存House对象
 * 响应HouseView的调用
 * 完成对房屋信息的增删改查
 */
public class HouseService {
//    属性
    private House houses[];  //保存House对象
    private int houseNums = 1;//记录当前有多少房屋信息
    private int idCounter = 1;//记录当前的id自增长到哪个值


//   构造器
    public HouseService(int size) {// 当创建HouseService对象时，指定数组的大小
        this.houses = new House[size];
        houses[0] = new House(1,"jack","110","宿州",2000,"未出租");
    }


//添加房屋信息操作
//add方法，添加新的对象，返回boolean
    public boolean add(House newHouse) {
        if (houseNums == houses.length){//房屋数量已经大于可容纳的数组大小
            System.out.println("数组已满，不能在添加");
            return false;
        }
//     把新的newHouse对象加入到
        houses[houseNums++] = newHouse;//houseNums++新增加了一个房屋信息
//    设置一个id自增长的机制,然后更新newHouse的id
        newHouse.setId(++idCounter);
        return true;
    }


//    list方法，返回houses
//    显示房屋列表功能
    public House[] list() {
        return houses;
    }


//    del()删除房屋信息操作
    public boolean del(int delId) {
//    应当先找到要删除的房屋信息对应的下标
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if (delId == houses[i].getId()) {//要删除的房屋(id),是数组下表为i的元素
                index = i; //记录i
            }
        }
        if (index == -1) {//说明delId在数组中不存在
            return false;
        }
        //找到删除的元素
//        如果找到第i个数组需要删除直接将i+1数组的信息覆盖到i数组中，然后将i+1=null
        for (int i = index; i < houseNums - 1; i++) {
            houses[i] = houses[i + 1];
        }
        houses[houseNums - 1] = null; //将存在的房屋数量最后一个置空
        houseNums --;
        return true;
    }


//    find()完成查询房屋信息的操作
    public House findId(int findId) { //House为引用类型 可以为null
        for (int i = 0; i < houseNums; i++) {
            if (findId == houses[i].getId()) {
                return houses[i];
            }
        }
        return null;
    }
}
