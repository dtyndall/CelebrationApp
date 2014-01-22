//
//  EventDetailViewController.m
//  jsontest2
//
//  Created by Chirag Patel on 1/17/14.
//  Copyright (c) 2014 STARS Computing Corps. All rights reserved.
//

#import "EventDetailViewController.h"
#import "Event.h"

@interface EventDetailViewController ()

@end

@implementation EventDetailViewController
@synthesize NameLabel,AuthorLabel,DescLabel,CategoryLabel,TrackLabel,SurveyLabel, EventModal;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
    Event *eventDetails = EventModal[0];
    NameLabel.text = [eventDetails eventName];
    AuthorLabel.text = [eventDetails eventAuthor];
    DescLabel.text = [eventDetails eventDesc];
    CategoryLabel.text = [eventDetails eventCategory];
    SurveyLabel.text = [eventDetails eventSurvey];
    
    self.navigationItem.title = [eventDetails eventName];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
