BEGIN {
	count1 = 0;
	count2 = 0;
	packet1 = 0;
	packet2 = 0;
	time1 = 0;
	time2 = 0;
}
{
	if($1=="r" && $3=="_1_" && $4=="AGT")
	{
		count1++ ;
		packet1 = packet1 + $8;
		time1 = $2;
	}
	
	if($1=="r" && $3=="_2_" && $4=="AGT")
	{
		count2++ ;
		packet2 = packet2 + $8;
		time2 = $2;
	}
}
END {
	printf("Throughput from n0 to n1: %f \n",((count1*packet1*8)/(time1*1000000)));
	printf("Throughput from n1 to n2: %f \n",((count2*packet2*8)/(time2*1000000)));
}
