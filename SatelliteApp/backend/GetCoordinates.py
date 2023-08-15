#!/usr/bin/python

import ephem
import sys
import os


def calculateCoords():
    # date = '2022/6/24'
    # line0 = 'ISS (ZARYA)'
    line1 = '1 00005U 58002B 21337.45953313 .00000109 00000-0 11481-3 0 9997'
    # line1 = '1 25544U 98067A   22162.52439360  .00005833  00000-0  11028-3 0  9999'
    line2 = '2 25544  51.6455   4.6361 0004468 222.6641 220.6469 15.49954017344301'

    if len(sys.argv) > 2:
        filename, date, line0, line1, line2 = sys.argv
        line0 = line0.rstrip('\n')
        line1 = line1.rstrip('\n')
        line2 = line2.rstrip('\n')
    sat = ephem.readtle(line0, line1, line2)
    # sat = ephem.readtle(line0, line1, line2)
    sat.compute(date)

    print("%s" % sat.sublat, ",")
    print("%s" % sat.sublong)


if __name__ == "__main__":
    calculateCoords()
