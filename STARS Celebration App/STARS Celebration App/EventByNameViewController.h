//
//  EventByNameViewController.h
//  STARS Celebration App
//
//  Created by Chirag Patel on 2/3/14.
//  Copyright (c) 2014 STARS Computing Corps. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Event.h"

@interface EventByNameViewController : UITableViewController

@property (nonatomic, strong) NSMutableArray *eventsArray;
@property (strong, nonatomic) NSMutableArray *json;


@end
