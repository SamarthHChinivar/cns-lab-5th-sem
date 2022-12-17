import java.io.*;
import java.util.Scanner;

public class dist_vec
{
	public static void main(String[] args)
	{
		int dmat[][];
		int dist[][];
		int via[][];
		
		int n,count,i,j,k = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Number of nodes: \n");
		n = sc.nextInt();
		
		dmat = new int[n][n];
		dist = new int[n][n];
		via = new int[n][n];
		
	  System.out.println("Enter cost matrix: \n");
	  for(i=0; i<n; i++)
	  	for(j=0; j<n; j++)
	  	{
	  		dmat[i][j] = sc.nextInt();
	  		dmat[i][i] = 0;
	  		dist[i][j] = dmat[i][j];
	  		via[i][j] = j;
	  	}
	  
	 do
	 {
	  	count = 0;
	  	for(i=0; i<n; i++)
	  		for(j=0; j<n; j++)
	  			for(k=0; k<n; k++)
	  			{
	  				if(dist[i][j] > dist[i][k]+dist[k][j])
	  				{
	  					dist[i][j] = dist[i][k]+dist[k][j];
	  					via[i][j] = k;
	  					count++;
	  				}
	  			}
	 } while(count != 0);
	 
	 for(i=0; i<n; i++)
	 {
	  	System.out.println("State value of router " +i+ "is: ");
	  	
	  	for(j=0; j<n; j++)
	  	{
	  		System.out.println("To " +j+ " -via " +via[i][j]+ " distance is " +dist[i][j]);
	  	}
	 }
	 
	}
	
}
