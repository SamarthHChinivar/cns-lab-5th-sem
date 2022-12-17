BEGIN{
	dcount = 0;
}

{
	event = $1;
	
	if(event == "d")
	{
		dcount++;
	}
}

END{
	printf("Number of packets dropped: %d \n", dcount);
}
