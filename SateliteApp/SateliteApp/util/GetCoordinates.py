#!/usr/bin/python

import ephem
import sys

line0 = 'ISS (ZARYA)             '
line1 = '1 25544U 98067A   22162.52439360  .00005833  00000-0  11028-3 0  9999'
line2 = '2 25544  51.6455   4.6361 0004468 222.6641 220.6469 15.49954017344301'

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    if len(sys.argv) > 2:
        filename, line0, line1, line2 = sys.argv

    sat = ephem.readtle(line0, line1, line2)
    date = ephem.now
    date = date[0] + "/" + date[1] + "/" + date[2]
    sat.compute(date)

    print("longitude: %s" % sat.sublong)
    print("%s" % sat.sublat)
