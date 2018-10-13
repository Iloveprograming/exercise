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

//�����������Inter(R)Core(TM)i5-4200 CPU @ 1.60GHz  2.30Ghz
//�Ѱ�װ�ڴ�(RAM)   6.00GB(5.89GB����)

public class ccc {
public static void main(String[] args) {
	
	
	/*------------HashSet-----------������ظ�����*/
	Set<Integer>set1=new HashSet<Integer>();
	int num,sum,j=0,i=0;
	num=1000000;             //��ѯ�ļ��ϴ�С
	sum=2000;              //��ѯ�����ָ���
	for(;j<num;j++)
		set1.add(j);
	long start1=System.currentTimeMillis();
	setInpuiry(set1,sum,num);
	long end1=System.currentTimeMillis();
	System.out.println("����ʱ����"+(end1-start1)+"ms");
	
	/*------------TreeSet-----------TreeSet�Ὣ�������򲢽��ظ���ɾ����*/
	Set<Integer>set2=new TreeSet<Integer>();
	j=0;i=0;
	for(;j<num;j++)
		set2.add(j);
	long start2=System.currentTimeMillis();
	setInpuiry(set2,sum,num);
	long end2=System.currentTimeMillis();
	System.out.println("����ʱ����"+(end2-start2)+"ms");
	
	
	/*------------LinkedList-----------*/
	List<Integer> list1=new LinkedList<Integer>();
	j=0;
	i=0;
	for(;j<num;j++)
		list1.add(j);
	long start3=System.currentTimeMillis();
	listInpuiry(list1,sum,num);
	long end3=System.currentTimeMillis();
	System.out.println("����ʱ����"+(end3-start3)+"ms");
	
	
	/*------------ArrayList-----------*/
	List<Integer> list2=new ArrayList<Integer>();
	i=0;
	j=0;
	for(;j<num;j++)
		list2.add(j);
	long start4=System.currentTimeMillis();
	listInpuiry(list2,sum,num);
	long end4=System.currentTimeMillis();
	System.out.println("����ʱ����"+(end4-start4)+"ms");

	/*------------HashMap-----------*/
	Map<Integer,Integer> map=new HashMap<Integer,Integer>();
	j=0;i=0;
	for(;j<num;j++)
		map.put(j, j);
	Random rand=new Random();
	int [] rannum=new int[sum];              
	for(int k=0;k<sum;k++)
		rannum[k]=rand.nextInt(num);             //��������������ڲ�ѯ
	long start5=System.currentTimeMillis();
	while(i<=sum)                   //����num�����ϵ�HashMap�в�ѯi�����key
	{
		if(i==sum) {
			System.out.println("�����HashMap�е�"+sum+"��������Ĳ�ѯ");
			break;
		}
		if(map.containsKey(rannum[i]))
			i++;
		else {
			System.out.println("�˼����в����ڴ�Key");
			break;
		}
	}
	long end5=System.currentTimeMillis();
	System.out.println("����ʱ����"+(end5-start5)+"ms");

}
public static void listInpuiry(List list,int sum,int boundary)//List����
{
	int i=0;
	 Random rand=new Random();
	int []rannum=new int[sum];          //���������������
	for(int k=0;k<sum;k++)
		rannum[k]=rand.nextInt(boundary);      //����������鸳ֵ
	 while(i<=sum)                    //�������в�ѯk�������
	{
		if(i==sum)
			{
			System.out.println("�����"+sum+"��������Ĳ�ѯ");
			break;
			}
		if(list.contains(rannum[i]))    //��ѯ���������޸�Ԫ��
			i++;
		else {
			System.out.println("�˼����в����ڸ�����������������߽��趨����");
			break;
		}
		
	}
}
public static void setInpuiry(Set set,int sum,int boundary)//Set����
	{
	int i=0;
	 Random rand=new Random();
	int []rannum=new int[sum];
	for(int k=0;k<sum;k++)
		rannum[k]=rand.nextInt(boundary);           //��������鸳ֵ
	 while(i<=sum)
	{
		if(i==sum)
			{
			System.out.println("�����"+sum+"��������Ĳ�ѯ");
			break;
			}
		if(set.contains(rannum[i]))
			i++;
		else {
			System.out.println("�˼����в����ڸ�����������������߽��趨����");
			break;
		}
		
	}
	}
}