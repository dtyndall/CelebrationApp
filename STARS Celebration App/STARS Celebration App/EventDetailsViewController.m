//
//  EventDetailsViewController.m
//  STARS Celebration App
//
//  Created by Chirag Patel on 2/3/14.
//  Copyright (c) 2014 STARS Computing Corps. All rights reserved.
//

#import "EventDetailsViewController.h"
#import "Event.h"
#import "EventByNameViewController.h"
#import "AppDelegate.h"

@interface EventDetailsViewController ()

@end

@implementation EventDetailsViewController

//Allows var usage without "_" prefix
@synthesize NameLabel, AuthorLabel, DescLabel, CategoryLabel, TrackLabel, SurveyLabel, EventModal, SelectedEvent, delegate;

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
    NameLabel.text = [SelectedEvent eventName];
    AuthorLabel.text = [SelectedEvent eventAuthor];
    DescLabel.text = [SelectedEvent eventDesc];
    CategoryLabel.text = [SelectedEvent eventCategory];
    SurveyLabel.text = [SelectedEvent eventSurvey];
    TrackLabel.text = [SelectedEvent eventTrack];
    
    delegate = (AppDelegate *) [[UIApplication sharedApplication] delegate];
    
    self.navigationItem.title = [SelectedEvent eventName];
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)favorited:(id)sender {
    if (![delegate.favoritedEvents containsObject:SelectedEvent]) {
    [delegate.favoritedEvents addObject:SelectedEvent];
    }
    else {
    NSLog(@"Object exists");
    }
}
@end
