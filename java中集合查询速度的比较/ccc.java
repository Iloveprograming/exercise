package com.cwy.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


import java.util.*;
import java.util.Random;

//计算机处理器Inter(R)Core(TM)i5-4200 CPU @ 1.60GHz  2.30Ghz
//已安装内存(RAM)   6.00GB(5.89GB可用)

public class ccc {
public static void main(String[] args) {
	
	
	/*------------HashSet-----------不存放重复数据*/
	Set<Integer>set1=new HashSet<Integer>();
	int num,sum,j=0,i=0;
	num=1000000;             //查询的集合大小
	sum=2000;              //查询的数字个数
	for(;j<num;j++)
		set1.add(j);
	long start1=System.currentTimeMillis();
	setInpuiry(set1,sum,num);
	long end1=System.currentTimeMillis();
	System.out.println("运行时间是"+(end1-start1)+"ms");
	
	/*------------TreeSet-----------TreeSet会将对象排序并将重复的删除了*/
	Set<Integer>set2=new TreeSet<Integer>();
	j=0;i=0;
	for(;j<num;j++)
		set2.add(j);
	long start2=System.currentTimeMillis();
	setInpuiry(set2,sum,num);
	long end2=System.currentTimeMillis();
	System.out.println("运行时间是"+(end2-start2)+"ms");
	
	
	/*------------LinkedList-----------*/
	List<Integer> list1=new LinkedList<Integer>();
	j=0;
	i=0;
	for(;j<num;j++)
		list1.add(j);
	long start3=System.currentTimeMillis();
	listInpuiry(list1,sum,num);
	long end3=System.currentTimeMillis();
	System.out.println("运行时间是"+(end3-start3)+"ms");
	
	
	/*------------ArrayList-----------*/
	List<Integer> list2=new ArrayList<Integer>();
	i=0;
	j=0;
	for(;j<num;j++)
		list2.add(j);
	long start4=System.currentTimeMillis();
	listInpuiry(list2,sum,num);
	long end4=System.currentTimeMillis();
	System.out.println("运行时间是"+(end4-start4)+"ms");

	/*------------HashMap-----------*/
	Map<Integer,Integer> map=new HashMap<Integer,Integer>();
	j=0;i=0;
	for(;j<num;j++)
		map.put(j, j);
	Random rand=new Random();
	int [] rannum=new int[sum];              
	for(int k=0;k<sum;k++)
		rannum[k]=rand.nextInt(num);             //定义随机数组用于查询
	long start5=System.currentTimeMillis();
	while(i<=sum)                   //在有num个集合的HashMap中查询i个随机key
	{
		if(i==sum) {
			System.out.println("已完成HashMap中的"+sum+"个随机数的查询");
			break;
		}
		if(map.containsKey(rannum[i]))
			i++;
		else {
			System.out.println("此集合中不存在此Key");
			break;
		}
	}
	long end5=System.currentTimeMillis();
	System.out.println("运行时间是"+(end5-start5)+"ms");

}
public static void listInpuiry(List list,int sum,int boundary)//List集合
{
	int i=0;
	 Random rand=new Random();
	int []rannum=new int[sum];          //定义随机数字数组
	for(int k=0;k<sum;k++)
		rannum[k]=rand.nextInt(boundary);      //给随机数数组赋值
	 while(i<=sum)                    //从数组中查询k个随机数
	{
		if(i==sum)
			{
			System.out.println("已完成"+sum+"个随机数的查询");
			break;
			}
		if(list.contains(rannum[i]))    //查询几何中有无该元素
			i++;
		else {
			System.out.println("此集合中不存在该数，可能是随机数边界设定错误");
			break;
		}
		
	}
}
public static void setInpuiry(Set set,int sum,int boundary)//Set集合
	{
	int i=0;
	 Random rand=new Random();
	int []rannum=new int[sum];
	for(int k=0;k<sum;k++)
		rannum[k]=rand.nextInt(boundary);           //随机数数组赋值
	 while(i<=sum)
	{
		if(i==sum)
			{
			System.out.println("已完成"+sum+"个随机数的查询");
			break;
			}
		if(set.contains(rannum[i]))
			i++;
		else {
			System.out.println("此集合中不存在该数，可能是随机数边界设定错误");
			break;
		}
		
	}
	}
}