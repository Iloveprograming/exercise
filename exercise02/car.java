
import java.util.Scanner;


public class car{            //������
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
	System.out.println("����    "+name+"   ��������    "+tank+"   �ͺ�      "+oilConsumption);
}
public car(){
name="��ɯ����";
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
int len=input.nextInt();                             //�û��������������
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
