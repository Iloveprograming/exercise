
import java.util.Scanner;


public class car{            //汽车类
String name;
double tank;
double oilConsumption;
public boolean gas(double content){
	if(content<tank/4)return true;
	else return false;
};
public void run(){};
public void display()
{
	System.out.println("车名    "+name+"   油箱容量    "+tank+"   油耗      "+oilConsumption);
}
public car(){
name="玛莎拉蒂";
tank=90.0;
oilConsumption=4.6;
}
public car(String iname,double itank,double ioilConsumption){
name=iname;
tank=itank;
oilConsumption=ioilConsumption;
}
public static void main(String [] args){           
Scanner input = new Scanner(System.in);
int len=input.nextInt();                             //用户输入的汽车数量
car [] s=new car[len];
String sname;
double stank,soilConsumption;
for(int i=0;i<len;i++)
{
	sname=input.next();
	stank=input.nextDouble();
	soilConsumption=input.nextDouble();
	s[i]=new car(sname,stank,soilConsumption);
}
for(int i=0;i<len;i++)
{
s[i].display();
}
}
};
