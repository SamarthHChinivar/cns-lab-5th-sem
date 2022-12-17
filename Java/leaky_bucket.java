import java.lang.*;
import java.io.*;
import java.util.Scanner;
import java.util.Random;

class leaky_bucket
{
  public static void main(String[] args)
  {
    int drop = 0, p_remain = 0, mini, nsec;
    
    int o_rate, b_size, i, packet[];
    
    packet = new int[100];
    
    Scanner sc = new Scanner(System.in);
    
    System.out.println("Enter Bucket size:");
    b_size = sc.nextInt();
    
    System.out.println("Enter Output rate:");
    o_rate = sc.nextInt();
    
    System.out.println("Enter the Number of seconds, you want to simulate:");
    nsec = sc.nextInt();
    
    Random r = new Random();
    
    for(i=0; i<nsec; i++)
        packet[i] = (r.nextInt(9) + 1) * 10;
        
    System.out.println("Seconds | Packets received | Packets");
    
    for(i=0; i<nsec; i++)
    {
      p_remain = p_remain + packet[i];
      
      if(p_remain > b_size)
      {
        drop = p_remain - b_size;
        p_remain = b_size;
        
        System.out.print(i+1+ " ");
        System.out.print(packet[i]+ " ");
        
        mini = Math.min(p_remain,o_rate);
        
        System.out.print(mini+" ");
        p_remain = p_remain - mini;
        
        System.out.print(p_remain+ " ");
        System.out.print(drop+ " ");
        System.out.println("");
        
        drop = 0;
      }
      
    }
    
    while(p_remain != 0)
    {
      if(p_remain > b_size)
      {
        drop = p_remain - b_size;
        p_remain = b_size;
      }
      
      mini = Math.min(p_remain,o_rate);
      
      System.out.print(" " +p_remain+ " " +mini);
      
      p_remain = p_remain - mini;
      
      System.out.print(p_remain+ " " +drop);
      System.out.println();
      drop = 0;
    }
    
  }
}
