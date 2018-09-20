public class BeerSong
{

public static void main(String [] args)
{

BeerSong a=new BeerSong();
int n=10;

String word="bottles";
for(int i=n;i>=0;i--)
{

if(i==1)
{
word="bottle";
a.print(i,word);
}
else if(i==0)
System.out.println("No more bottles on the wall.");
else
{
a.print(i,word);
}
}
}
public void print(int i,String a)
{
System.out.println(i+" "+a+" of beer on the wall."+i+" "+a+" bottles of beer.");

System.out.println("Take one down.");

System.out.println("Pass it around.");

System.out.println((i-1)+" "+a+" of beer on the wall.");
System.out.println();
}
}