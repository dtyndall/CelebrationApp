-- phpMyAdmin SQL Dump
-- version 4.0.6
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 03, 2013 at 05:18 AM
-- Server version: 5.5.33
-- PHP Version: 5.5.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `conference`
--

CREATE TABLE `conference` (
  `Conf_Id` int(4) NOT NULL AUTO_INCREMENT,
  `Conf_Name` varchar(30) DEFAULT NULL,
  `Conf_year` int(4) DEFAULT NULL,
  `Conf_location` varchar(100) DEFAULT NULL,
  `Conf_map` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`Conf_Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `conference`
--

INSERT INTO `conference` (`Conf_Id`, `Conf_Name`, `Conf_year`, `Conf_location`, `Conf_map`) VALUES
(7, 'test conf', 0, '', 'http://cdn.memegenerator.net/instances/500x/43135225.jpg'),
(8, 'stars2014', 2014, 'chicken', 'http://zef.me/wp-content/uploads/2009/09/funny-cat.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `Conf_year` int(10) DEFAULT NULL,
  `event_name` varchar(100) DEFAULT NULL,
  `author_name` varchar(100) DEFAULT NULL,
  `event_description` text,
  `event_category` varchar(15) DEFAULT NULL,
  `survey` varchar(1000) DEFAULT NULL,
  `track` text,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`event_id`, `Conf_year`, `event_name`, `author_name`, `event_description`, `event_category`, `survey`, `track`) VALUES
(5, 2014, 'First Generation College Students in Com', 'Carly, The Dude', 'Due to an increase of overall enrollment in universities and colleges nationwide in the past decades, it is disconcerting to note that the population of first generation college students did not also experience such an increase. First generation students are just as likely as their non-first generation counterparts to be accepted to college, according to the American Freshmen: National Norms of Fall 2011 published by the Cooperative Institution Research Program. However, the first generation college students are less likely to matriculate. Although this report does not specify the success based on types of universities (i.e. engineering vs. liberal arts), it is clear to see that first generation college students are a population both in high school and college that should get extra support and attention for recruitment and retention in computer science. First generation college students are a community that we can extend an extra helping hand and support to when trying to get more students involved in computing. Members of this particular community typically do not have access to someone involved in computing to interest them in the field, especially with the lack of Computer Science education in the school systems. This Birds-of-a-Feather session will hopefully bring together fellow first generation college students in the field of computing, as well as staff and faculty who could have a direct impact on the population at the high school level or at their respective colleges and institutions. The session will range from discussing how we ourselves became involved in computing to how to improve the involvement of first generation college students in our computing focused programs in high schools and can expand to what support can be provided for the population at our current universities. The session should also provide some insight into furthering education and careers in computing for first generation college students and providing advice in preparation for graduate school.', 'Presentation', 'http://www.moodle2.uncc.edu', 'Civic Engagement'),
(6, 2014, 'How to run a scratch competition', 'Carly', 'I am proposing a presentation on how to run a Scratch competition. I created a manual that contains detailed information about how to organize all aspects of the competition. The presentation will include the following:  Why hold a Scratch competition? What age groups should be eligible?  How can you handle submission of the entries?  Suggestions for the requirements for the entries  Who should judge the entries  How to judge the entries  Suggestions for the types and number of prizes  Suggestions for how to pay for the prizes  Suggestions for the awards ceremony  Georgia Techâ€™s Institute for Computing Education (ICE) has been running a Scratch competition for 4th â€“ 12th grade students since 2010. The competition has grown from 52 entries from 74 students in 2010 to 81 entries from 133 students in Fall 2012. Students earn prizes like Beats headphones, iHome iPod docks, drawing tablets, printers, etc. The competition encourages teachers to use Scratch and encourages students to spend extra time on Scratch projects.', 'Presentation', 'http://www.google.com', 'Leadership'),
(7, 2014, 'Building a Portfolio of K12 STEM outreac', 'Bill, Dr. Haynes', 'STEM is recently at the forefront of major educational efforts in the U.S. (although we-STARS have been talking about this since 2005). FSU STEM efforts are focused on building networks across the County and in the K-12 communities we support to enhance the effectiveness of STEM education and support STARS efforts to bring more women and minorities into computing/IT.', 'Presentation', 'http://www.google.com', 'Corps Practices'),
(8, 2014, 'Break', 'Steve', 'edited event', 'Presentation', 'http://www.reddit.com', 'Technical Excellence'),
(9, 2014, 'Android', 'Derek Banas', 'learning it up', 'Presentation', 'http://www.tenmanga.com', 'Leadership'),
(11, 2014, 'Android adding', 'Google mostly ', 'Made this from my phone', 'Presentation', 'http://www.google.com', 'Civic Engagement'),
(12, 2014, 'Applying Universal Design', 'Lois Richardson', 'This 60-minute session will be led by staff from the AccessComputing Alliance, which works to increase the participation of people with disabilities in computing fields. Participants will leave the session with an understanding of what universal design of learning is and how it can make outreach activities more welcoming and accessible for individuals from a broad range of backgrounds, especially those with disabilities. During the session, we will talk about specific examples of universal design in computing activities. Participants will also leave with a number of suggestions for ways to apply universal design to their activities with respect to climate, interaction, physical environments and products, delivery methods, information resources and technology, and accommodation. In addition, participants will learn about a variety of online and print resources that they can use related to universal design.', 'Presentation', 'http://www.google.com', 'Corps Practices'),
(13, 2014, 'Encountering Difficulties', 'Dr. Haynes, Bill Buxely, The Dude, Bugs Bunny', 'Encountering difficulties is part of our experience as leaders, and sometimes we can get demotivated. As a consequence, the feeling of being overwhelmed and frustrated by not being able to follow what was planned in the beginning can make a difference on how your message of STEM is delivered. For this reason, we forget how simple can be to improvise if we know what we need to do and what tools do we have. This presentation will cover how to overcome difficulties when delivering an outreach workshop and we will discuss many simple useful tools that we can implement when improvising. Point to cover: Knowing the tools that you work with. Implementing back up plans. Simple workshops. Open source resources online. Exchanging tips (Network). Involving more your volunteers.', 'Presentation', 'http://www.google.com', 'Technical Excellence'),
(14, 2014, 'Fofective Outreach using App Inventor to Create Android Apps', 'Barbara Ericson, Georgia Tech', 'Georgia Tech has been using App Inventor in its summer camps, weekend workshops, and teacher workshops since 2012. Our summer camps get statistically significant changes in attitudes towards computing and in knowledge of computing concepts. Come and get some hands-on experience with creating apps using App Inventor. The workshop will include lessons learned from using App Inventor with 4th - 12th grade students as well as high school teachers. We can provide laptops and cell phones for up to 35 participants.', 'Presentation', 'http://www.google.com', 'Corps Practices'),
(15, 2014, 'Where''s your STARS website - an Introduction to Drupal', 'Scott Heggen, University of North Carolina at Charlotte', 'Every STARS Corps is expected to host a website highlighting all their wonderful accomplishments, yet many don''t. This workshop will help faculty and students take the first step to creating their STARS website using the flexible, powerful Drupal content management system. Motivated by the lessons learned designing the new STARS Computing Corps main site, this workshop will focus on designing an appealing and highly functional website to showcase your Corps'' work. The workshop will take you through the entire process, starting with the installation process for a new Drupal site, demystifying the terminology used in the Drupal community, and configuring advanced modules to serve the multitude of specific functions that Drupal provides. By the end of this workshop, you''ll have the foundation for a website to represent your Corps. This workshop will require no prior skills in web development; all you need is a laptop.', 'Presentation', 'http://www.google.com', 'Leadership');

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE `session` (
  `Location` varchar(20) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Time` time DEFAULT NULL,
  `event_id` int(11) DEFAULT NULL,
  `session_id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `session`
--

INSERT INTO `session` (`Location`, `Date`, `Time`, `event_id`, `session_id`) VALUES
('Room 404', '2014-08-13', '18:00:00', 6, 1),
('Room 404', '2014-08-13', '08:00:00', 5, 3),
('Room 404', '2014-08-13', '17:00:00', 13, 5),
('Room 404', '2014-08-13', '12:00:00', 8, 6),
('Room 545', '2014-08-13', '13:42:00', 9, 9),
('Room 545', '2014-08-12', '13:45:00', 11, 12),
('Room 4324', '2014-08-12', '11:00:00', 13, 15),
('Room 4324', '2014-08-12', '10:00:00', 12, 16),
('Room 325', '2014-08-11', '11:00:00', 14, 17),
('Room 325', '2014-08-11', '15:30:00', 15, 18);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
