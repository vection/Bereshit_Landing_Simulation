# Bereshit_Landing_Simulation

This simulation was built by inspiration of SpaceIL project - Bereshit.
Which is mission to land on the moon, more details can be found -  https://en.wikipedia.org/wiki/SpaceIL

Bereshit spacecraft unfortunately didn't manage to land properly on the moon, there are many speculations and thoughts about the real reason of this failure, alot of reasons can be found on the interenet or SpaceIL site.

The main goal of this simulator is try to make landing process successfully which will do it fully automatically using all sensors available in the craft.

We have here few parameters we count on: 
Vertical speed, Horizontal speed, distance, angle-rotation, alttitude,acceleration,fuel,weight.

All these parameters and few more will calculate every frame, it can be either 1 per second or even more iterations per second.

In the main you can define it, CPU class is acting like real CPU with power process and it handles with evaluating functions real time.
The main engine role here to controll the angle of the craft with 8 small engines. 

There is no graphics in this simulation, so the only way to see what happend is debug mode on which prints details every frame.
More info about the Bereshit craft can be found -  https://en.wikipedia.org/wiki/Beresheet

In this simulation I managed to land from 13748 m (according to this scenario https://www.youtube.com/watch?v=JJ0VfRL9AMs 2:25:40) in around 10 minutes and 250km distance to landing.


