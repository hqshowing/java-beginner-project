package project.smallchange.OOP;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*
该类是完成零钱通的各个功能的类
使用OOP(面对对象编程)
将各个功能对应一个方法
 */
public class SmallChangeOOP {
    //定义属性
    // 1. 先完成显示菜单，并可以选择菜单，给出对应的提示
    //定义相关变量
    boolean loop = true;  //循环显示菜单
    Scanner scanner = new Scanner(System.in);
    String key = "";
    // 2. 完成零钱通明细
    // 使用字符串拼接
    String details = "----------零钱通明细----------";
    // 3. 收益入账
    // 需要增加新的变量 保存收益的金额以及余额 以及对收入金额进行校验
    double money = 0;
    double balance = 0;
    Date date = null; // date 是java.util.Date类型 表示日期
    SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm");//用于日期的格式化 年-月-日 时:分
    // 4. 消费
    // 定义新的变量，保存消费的原因 以及对余额进行校验
    String note = "";
    // 5. 退出
    //定义新的的变量 去接受输入的y/n
    String choice = "";

    //1. 显示主菜单 并可以选择
    public void mainMenu() {
        do {
            System.out.println("=======零钱通菜单(OOP)=======");
            System.out.println("\t\t1 零钱通明细");
            System.out.println("\t\t2 收益入账");
            System.out.println("\t\t3 消费");
            System.out.println("\t\t4 退出");
            System.out.print("请选择(1-4): ");
            key = scanner.next();
            //使用switch去分支选择
            switch (key) {
                case "1":
                    this.details();
                    break;
                case "2":
                    this.income();
                        break;
                case "3":
                    this.pay();
                        break;
                case "4":
                  this.exit();
                        break;
                default:
                    System.out.println("选择有误，请重新选择");
            }
        } while (loop);
        System.out.println("------零钱通已经退出------");
    }
    // 2. 完成零钱通明细
    public void details() {
        System.out.println(details);
    }
    //3. 收益入账
    public void income() {
        System.out.print("收益的金额为: ");
        money = scanner.nextDouble(); //收益
        // money的值范围应该校验 可以从正反两面进行校验 正面：money > 0时(...) 反面：money < 0(...)
        if (money <= 0) {
            System.out.println("收益金额需要大于0");
            return;
        }
        balance += money; //余额
        date = new Date();
        details += "\n收益入账\t+" + money + "\t" + sdf.format(date) + "\t" + balance;
    }
    //4. 消费
    public void pay() {
        System.out.print("消费金额: ");
        money = scanner.nextDouble();
        //消费金额的校验 有正反两名 正面：当money > 0 || money > balance (...)
        // 反面money < 0 () || money > balance
        if (money <= 0 || money > balance){ //校验消费的金额是否在0~balance之间
            System.out.println("消费金额应该在0~" + balance + "之间");
            return;
        }
        System.out.print("消费原因: ");
        note = scanner.next();
        balance -= money;
        date = new Date();
        details += "\n\t" + note + "\t-" + money + "\t" + sdf.format(date) + "\t" + balance;
    }
    //5. 退出
    public void exit() {
        while (true) {
            System.out.println("你确定要退出么？y/n");
            choice = scanner.next();
            if ("y".equals(choice) || "n".equals(choice)) {//判断输入的是y/n 只用输入的是y和n时才能退出循环
                break;
            }
        }
        if (choice.equals("y")) {//判断choice是y还是n 如果是y则退出大循环 如果是n不做任何处理，继续执行大循环
            loop = false;
        }
    }
}

