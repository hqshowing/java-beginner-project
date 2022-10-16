package project.houserent.view;

import project.houserent.domain.House;
import project.houserent.service.HouseService;
import project.houserent.utils.Utility;

/**
 * 1.显示界面
 * 2.接收用户的输入
 * 3.调用HouseService完成对房屋信息的各种操作
 */
public class HouseView {
    //属性
    private boolean loop = true; //用于控制显示菜单信息
    private char key = ' ';//接受用户的输入

    //显示主菜单
    public void mainMenu() {
        do {
            System.out.println("------------房屋出租系统菜单-----------");
            System.out.println("\t\t\t1. 新 增 房 源");
            System.out.println("\t\t\t2. 查 找 房 屋");
            System.out.println("\t\t\t3. 删 除 房 屋 信 息");
            System.out.println("\t\t\t4. 修 改 房 屋 信 息");
            System.out.println("\t\t\t5. 房 屋 列 表");
            System.out.println("\t\t\t6. 退      出");
            System.out.print("请输入你的选择(1 - 6):");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    updateHouse();
                    break;
                case '5':
                    listHouse();
                    break;
                case '6':
                    exit();
                    break;
            }
        } while (loop);
    }


    //    显示房屋列表
//    需要调用HouseService中的list()方法返回房屋列表信息
    private HouseService houseService = new HouseService(3);

    public void listHouse() {
        System.out.println("----------------房屋列表---------------");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        House houses[] = houseService.list(); //list()返回的是houses数组，需要House[]接受
        for (int i = 0; i < houses.length; i++) {

            if (houses[i] == null) {//如果房屋信息为空，就不要输出
                break;
            }
            System.out.println(houses[i]); //重写的toString()方法
        }
        System.out.println("------------房屋列表显示完毕-----------\n");
    }


    //    显示添加房屋信息的功能
//    编写addHouse()接受输入，创建House对象，调用add方法
    public void addHouse() {
        System.out.println("------------添加房屋-----------");
        System.out.print("姓名：");
        String name = Utility.readString(8);
        System.out.print("电话：");
        String phone = Utility.readString(12);
        System.out.print("地址：");
        String address = Utility.readString(16);
        System.out.print("月租：");
        int rent = Utility.readInt();
        System.out.print("状态：");
        String state = Utility.readString(3);
//        创建一个新的House对象，注意id是系统分配，用户不能输入
        House newHouse = new House(0, name, phone, address, rent, state);
        if (houseService.add(newHouse)) {
            System.out.println("------------添加房屋成功-----------\n");
        } else {
            System.out.println("------------添加房屋失败-----------\n");
        }
    }


    //    显示删除房屋信息的功能
//    编写delHouse()，接受输入的id，调用del()
    public void delHouse() {
        System.out.println("------------删除房屋------------");
        System.out.print("请选择待删除房屋的编号(-1退出)：");
        int delId = Utility.readInt(); //delId 去接收用户输入的id要删除的id号
        if (delId == -1) {
            System.out.println("----------放弃删除房屋----------\n");
            return;
        }
//        System.out.print("请输入你的选择(Y/N)：");
        char choice = Utility.readConfirmSelection();//该方法本身就有循环判断的逻辑，必须输入Y和N;
        if (choice == 'Y') {//确认删除
            if (houseService.del(delId)) {
                System.out.println("----------删除房屋成功----------\n");
            } else {
                System.out.println("----------房屋id不存在----------\n");
            }
        } else {
            System.out.println("----------放弃删除房屋----------\n");
        }
    }


    //    完善退出功能，需要确定才能退出
    public void exit() {
//    使用Utility方法 完成确认删除
        char c = Utility.readConfirmSelection();
        if (c == 'Y') {
            loop = false;
        }
    }


    //    显示用户查询指定房屋信息
//    编写findHouse(),显示和接受用户输入的id
    public void findHouse() {
        System.out.println("------------查找房屋------------");
        System.out.print("请输入你要查询的id：");
        int findId = Utility.readInt();
        House house = houseService.findId(findId);
        if (house != null ) {
            System.out.println("------------查询成功------------");
            System.out.println(house + "\n");
        }else{
            System.out.println("------------查询失败------------\n");
        }
    }


    //    显示用户修改房屋信息
//    编写updateHouse()，接受瀛用户输入的id，
    public void updateHouse() {
        System.out.println("------------修改房屋信息------------");
        System.out.print("请选择待修改房屋编号(-1退出)：");
        int updateId = Utility.readInt(); //updateId接受用户输入的要修改房屋信息的Id
        if (updateId == -1){
            System.out.println("------------放弃修改房屋信息------------");
            return;
        }
//        根据输入的updateId查找对象
        House house = houseService.findId(updateId);
        if (house == null){
            System.out.println("------------房屋信息不存在------------\n");
            return;
        }else {
            //修改姓名
            System.out.print("姓名(" + house.getName() + "):");
            String name = Utility.readString(8,"");//如果用户回车表示不修改信息，默认为""
            if (!"".equals(name)) {
                house.setName(name);
            }
            //修改电话
            System.out.print("电话(" + house.getPhone() + "):");
            String phone = Utility.readString(12, "");
            if (!"".equals(phone)) {
                house.setPhone(phone);
            }
            //修改地址
            System.out.print("地址(" + house.getAddress() + "):");
            String address = Utility.readString(12, "");
            if (!"".equals(address)) {
                house.setAddress(address);
            }
            //修改租金
            System.out.print("租金(" + house.getRent() + "):");
            int rent = Utility.readInt(-1);
            if (rent != -1){
                house.setRent(rent);
            }
            //修改状态
            System.out.print("状态(" + house.getState() + "):");
            String state = Utility.readString(3, "");
            if (!"".equals(state)) {
                house.setState(state);
            }
            System.out.println("------------修改成功------------\n");
        }
    }
}


