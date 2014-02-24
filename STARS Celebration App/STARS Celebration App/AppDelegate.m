//
//  AppDelegate.m
//  STARS Celebration App
//
//  Created by Chirag Patel on 2/3/14.
//  Copyright (c) 2014 STARS Computing Corps. All rights reserved.
//

#import "AppDelegate.h"
#import "Event.h"

@implementation AppDelegate
@synthesize eventsArrayAD, favoritedEvents;

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    // Override point for customization after application launch.
    self.favoritedEvents = [[NSMutableArray alloc] init];
    [self retrieveData];
    return YES;
}
							
- (void)applicationWillResignActive:(UIApplication *)application
{
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later. 
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication *)application
{
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}

- (void) retrieveData
{
    NSMutableArray *json;
    NSString *getAllEventsURL = @"http://starscomputingcorps.org/celebrationApp/getAllEvents.php";
    NSURL * url = [NSURL URLWithString:getAllEventsURL];
    NSData * data = [NSData dataWithContentsOfURL:url];
    NSDictionary *temp = [[NSDictionary alloc] init];
    
    temp = [NSJSONSerialization JSONObjectWithData:data options:kNilOptions error:nil];
    json = [temp objectForKey:@"events"];
    eventsArrayAD = [[NSMutableArray alloc] init];
    
    for (int i = 0; i < json.count; i++) {
        //Create event objects
        NSString * eID = [[json objectAtIndex:i] objectForKey:@"event_id"];
        NSString * eName = [[json objectAtIndex:i] objectForKey:@"event_name"];
        NSString * eAuthor = [[json objectAtIndex:i] objectForKey:@"author_name"];
        NSString * eDesc = [[json objectAtIndex:i] objectForKey:@"event_description"];
        NSString * eCat = [[json objectAtIndex:i] objectForKey:@"event_category"];
        NSString * eSurvey = [[json objectAtIndex:i] objectForKey:@"survey"];
        NSString * eTrack = [[json objectAtIndex:i] objectForKey:@"track"];
        
        Event * myEvent = [[Event alloc] initWithEventID:eID andEventName:eName andEventAuthor:eAuthor andEventCategory:eCat andEventTrack:eTrack andEventSurvey:eSurvey andEventDesc:eDesc];
        
        //Adds event to events array
        [eventsArrayAD addObject:myEvent];
    }
    
}

@end
