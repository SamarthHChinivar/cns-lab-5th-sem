BEGIN{
	dcount = 0;
	rcount = 0;
}

{
	event = $1;
	
	if(event == "d")
	{
		dcount++;
	}
	
	if(event == "r")
	{
		rcount++;
	}
}

END{
	printf("Number of Packets Dropped: %d \n", dcount);
	printf("Number of Packets Dropped: %d \n", rcount);
}
