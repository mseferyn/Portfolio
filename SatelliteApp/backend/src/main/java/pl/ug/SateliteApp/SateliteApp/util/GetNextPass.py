#!/usr/bin/python

import ephem
import sys

target_long = ephem.degrees('18.6667')
target_lat = ephem.degrees('54.3500')

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    date = '2022/6/24'
    lat = 54.3500
    lon = 18.6667
    line0 = 'ISS (ZARYA)             '
    line1 = '1 25544U 98067A   22162.52439360  .00005833  00000-0  11028-3 0  9999'
    line2 = '2 25544  51.6455   4.6361 0004468 222.6641 220.6469 15.49954017344301'

if len(sys.argv) > 2:
    filename, line0, line1, line2, lon, lat = sys.argv

sat = ephem.readtle(line0, line1, line2)

userLoc = ephem.Observer()
userLoc.date = date
# userLoc
userLoc.lat = lat
userLoc.lon = lon
userLoc.elevation = 10
info = userLoc.next_pass(sat)

result = "Rise time: %s azimuth: %s" % (info[0], info[1])
print(""
      "%s" % info[0])
print("%s" % info[1])
