set ns [new Simulator]
set nd [open prog4.tr w]
$ns trace-all $nd

set topo [new Topography]
$topo load_flatgrid 1000 1000
set nf [open prog4.nam w]
$ns namtrace-all-wireless $nf 1000 1000

proc finish {} {
global ns nd nf
$ns flush-trace
close $nd
exec nam prog4.nam &
exit 0
}

$ns node-config -adhocRouting AODV \
-llType LL \
-macType Mac/802_11 \
-ifqType Queue/DropTail/PriQueue \
-ifqLen 50 \
-antType Antenna/OmniAntenna \
-propType Propagation/TwoRayGround \
-phyType Phy/WirelessPhy \
-channelType Channel/WirelessChannel \
-topoInstance $topo \
-agentTrace ON \
-routerTrace ON \
-movementTrace ON \

create-god 3
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

$n0 set X_ 50
$n0 set Y_ 50
$n0 set Z_ 0
$n1 set X_ 100
$n1 set Y_ 100
$n1 set Z_ 0
$n2 set X_ 600
$n2 set Y_ 600
$n2 set Z_ 0

$ns at 0.1 "$n0 setdest 50 50 15"
$ns at 0.1 "$n1 setdest 100 100 25"
$ns at 0.1 "$n2 setdest 600 600 25"

set tcp1 [new Agent/TCP]
$ns attach-agent $n0 $tcp1
set sink1 [new Agent/TCPSink]
$ns attach-agent $n1 $sink1
$ns connect $tcp1 $sink1
set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1
 
set tcp2 [new Agent/TCP]
$ns attach-agent $n1 $tcp2
set sink2 [new Agent/TCPSink]
$ns attach-agent $n2 $sink2
$ns connect $tcp2 $sink2
set ftp2 [new Application/FTP]
$ftp2 attach-agent $tcp2

$ns at 5 "$ftp1 start"
$ns at 5 "$ftp2 start"
$ns at 100 "$n1 setdest 500 500 15"
$ns at 150 "$n2 setdest 70 70 15"
$ns at 200 "finish"
$ns run
