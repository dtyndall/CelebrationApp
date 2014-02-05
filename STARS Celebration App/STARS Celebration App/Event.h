//
//  Event.h
//  STARS Celebration App
//
//  Created by Chirag Patel on 2/3/14.
//  Copyright (c) 2014 STARS Computing Corps. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Event : NSObject

@property (nonatomic, strong) NSString * eventID;
@property (nonatomic, strong) NSString * eventName;
@property (nonatomic, strong) NSString * eventAuthor;
@property (nonatomic, strong) NSString * eventCategory;
@property (nonatomic, strong) NSString * eventTrack;
@property (nonatomic, strong) NSString * eventSurvey;
@property (nonatomic, strong) NSString * eventDesc;

//Init Method
-(id) initWithEventID: (NSString *) eID andEventName: (NSString *) eName andEventAuthor: (NSString *) eAuthor andEventCategory: (NSString *) eCat andEventTrack: (NSString *) eTrack andEventSurvey: (NSString *) eSurvey andEventDesc: (NSString *) eDesc;

@end
