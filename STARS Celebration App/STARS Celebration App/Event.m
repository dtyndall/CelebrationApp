//
//  Event.m
//  STARS Celebration App
//
//  Created by Chirag Patel on 2/3/14.
//  Copyright (c) 2014 STARS Computing Corps. All rights reserved.
//

#import "Event.h"

@implementation Event
//allows use of vars without "_" prefix
@synthesize eventID, eventAuthor, eventCategory, eventDesc, eventName, eventSurvey, eventTrack;

//Init method
-(id) initWithEventID: (NSString *) eID andEventName: (NSString *) eName andEventAuthor: (NSString *) eAuthor andEventCategory: (NSString *) eCat andEventTrack: (NSString *) eTrack andEventSurvey: (NSString *) eSurvey andEventDesc: (NSString *) eDesc;
{
    self = [super init];
    if (self)
    {
        eventID = eID;
        eventName = eName;
        eventAuthor = eAuthor;
        eventDesc = eDesc;
        eventCategory = eCat;
        eventSurvey = eSurvey;
        eventTrack = eTrack;
    }
    return self;
}

- (void)encodeWithCoder:(NSCoder *)coder
{
    [coder encodeObject:eventID forKey:@"eventID"];
    [coder encodeObject:eventAuthor forKey:@"eventAuthor"];
    [coder encodeObject:eventName forKey:@"eventName"];
    [coder encodeObject:eventDesc forKey:@"eventDesc"];
    [coder encodeObject:eventCategory forKey:@"eventCategory"];
    [coder encodeObject:eventSurvey forKey:@"eventSurvey"];
    [coder encodeObject:eventTrack forKey:@"eventTrack"];
}

- (id)initWithCoder:(NSCoder *)coder
{
    self = [[Event alloc] init];
    if (self != nil)
    {
        eventID = [coder decodeObjectForKey:@"eventID"];
        eventAuthor = [coder decodeObjectForKey:@"eventAuthor"];
        eventName = [coder decodeObjectForKey:@"eventName"];
        eventDesc = [coder decodeObjectForKey:@"eventDesc"];
        eventCategory = [coder decodeObjectForKey:@"eventCategory"];
        eventSurvey = [coder decodeObjectForKey:@"eventSurvey"];
        eventTrack = [coder decodeObjectForKey:@"eventTrack"];
    }
    return self;
}

@end
