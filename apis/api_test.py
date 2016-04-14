#!/usr/bin/env python3
'''
    This is a program built from the example program given in CS257 by
    Professor Jeff Ondich. This program uses Google place API to get
    all nearby restaurants once given a location and a distance from the
    location. After returning all the name of the restaurants, users can
    also query for the details of those retaurants.
'''  

import sys
import argparse
import json
import urllib.request
   
def get_restaurants(location_la, location_lo, radius):
    '''
    Get the names of all restaurants within a distance from a location.
    The query returns some information about the restaurant but we are
    not going to use them as details. We are going to query again to
    another API call Google Place Details to get better information about
    the restaurant.
    '''
    base_url = 'https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={0},{1}&radius={2}&type=restaurant&key=AIzaSyB0Hh0DFNk-xNXHDZuA9dRAxGTJRcCV1cA'
    url = base_url.format(location_la, location_lo, radius)
    # get data from the server
    try:
        data_from_server = urllib.request.urlopen(url).read()
        string_from_server = data_from_server.decode('utf-8')
        json_from_server = json.loads(string_from_server)
    except Exception as e:
        # Problems with network access or JSON parsing.
        return []
    # put the data into result_list and return them
    result_list = []
    for restaurant in json_from_server['results']:
        result_list.append(restaurant)
    return result_list


def get_details(place_id):
    '''
    Get details of the restaurant from its place_id.
    This is used after the restaurant lists are given.
    The place_id is retrieved from the information given
    in the previous query.
    '''
    base_url = 'https://maps.googleapis.com/maps/api/place/details/json?placeid={0}&key=AIzaSyB0Hh0DFNk-xNXHDZuA9dRAxGTJRcCV1cA'
    url = base_url.format(place_id)
    # get data from the server as json_from_server
    try:
        data_from_server = urllib.request.urlopen(url).read()
        string_from_server = data_from_server.decode('utf-8')
        json_from_server = json.loads(string_from_server)
    except Exception as e:
        # Problems with network access or JSON parsing.
        return {}
    return json_from_server['result']

def main(args):
    # search for restaurants
    restaurant_list = get_restaurants(args.latitude, args.longitude, args.radius)
    index = 1

    # list and print them
    print ('**** Search Results ****')
    for restaurant in restaurant_list:
        name = restaurant['name']
        print ('{0}. {1}'.format(index, name))
        index = index + 1
    print ('************************')

    # let the users get details of the restaurants
    while True:
        try:
            selected_index = input('Please enter an index to get details (or \'quit\' to quit):')
            if selected_index == 'quit':
                break
            print ('---------- {0} ----------'.format(restaurant_list[int(selected_index) - 1]['name']))

            # query again to get the details
            details = get_details(restaurant_list[int(selected_index) - 1]['place_id'])
            print (details['formatted_address'])
            print (details['formatted_phone_number'])
            if details['opening_hours']['open_now']:
                print ('Open')
            else:
                print ('Closed')
            for time in details['opening_hours']['weekday_text']:
                print (time)
            print ("Price Level: {0}".format(details['price_level']))
            print ("Rating: {0}".format(details['rating']))
            print ("Find on Map: {0}".format(details['url']))
            print ('Website: {0}'.format(details['website']))
        except Exception:
            print ('Please enter a valid input')

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Get all restaurants near a location from Google Maps')

    parser.add_argument('latitude',
                        type=float,
                        metavar='latitude',
                        help='The latitude of where you start')

    parser.add_argument('longitude',
                        type=float,
                        metavar='longitude',
                        help='The longitude of where you start')

    parser.add_argument('radius', 
                        metavar='radius',
                        type=float,
                        help='the radius of the area you want to search from the location')

    args = parser.parse_args()
    main(args)
