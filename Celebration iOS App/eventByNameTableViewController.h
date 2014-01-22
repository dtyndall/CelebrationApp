//
//  eventByNameTableViewController.h
//  jsontest2
//
//  Created by Chirag Patel on 1/16/14.
//  Copyright (c) 2014 STARS Computing Corps. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Event.h"

@interface eventByNameTableViewController : UITableViewController

@property (nonatomic, strong) NSMutableArray *eventsArray;
@property (strong, nonatomic) NSMutableArray *json;
#pragma mark - Methods
- (void) retrieveData;

@end
