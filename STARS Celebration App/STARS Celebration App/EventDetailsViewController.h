//
//  EventDetailsViewController.h
//  STARS Celebration App
//
//  Created by Chirag Patel on 2/3/14.
//  Copyright (c) 2014 STARS Computing Corps. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Event.h"
#import "AppDelegate.h"

@interface EventDetailsViewController : UIViewController

@property (nonatomic, strong) IBOutlet UILabel *NameLabel;
@property (nonatomic, strong) IBOutlet UILabel *AuthorLabel;
@property (nonatomic, strong) IBOutlet UILabel *DescLabel;
@property (nonatomic, strong) IBOutlet UILabel *CategoryLabel;
@property (nonatomic, strong) IBOutlet UILabel *TrackLabel;
@property (nonatomic, strong) IBOutlet UILabel *SurveyLabel;

@property (nonatomic, strong) NSMutableArray *EventModal;

@property (nonatomic, strong) Event *SelectedEvent;
@property (nonatomic, strong) AppDelegate *delegate;

@end
