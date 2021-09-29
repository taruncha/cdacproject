package com.gym.GymLocator.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate.BooleanOperator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.gym.GymLocator.entity.Booking;
import com.gym.GymLocator.entity.Gymregister;
import com.gym.GymLocator.entity.Instructor;
import com.gym.GymLocator.entity.User;
import com.gym.GymLocator.service.BookingService;
import com.gym.GymLocator.service.GymService;
import com.gym.GymLocator.service.InstructorService;
import com.gym.GymLocator.service.UserService;

@Controller
public class gymController implements HttpSessionListener {

	@Autowired
	private UserService userService;

	@Autowired
	private GymService gymService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private InstructorService instructorService;

	int userId;
	int bookId;
	int validUserId;

	@RequestMapping(path = { "/", "/gym", "nearBy" })
	public String home(@RequestParam(required = false, value = "loc") String cityName, HttpServletRequest req,
			HttpServletResponse res, Model model) {

		HttpSession session = req.getSession(false);
		if (session != null) {
			String usname = (String) session.getAttribute("unm");
			String gymuname = (String) session.getAttribute("gunm");
			if (usname != null && gymuname == null) {
				Gymregister g = new Gymregister();
				g.setCity(cityName);
				List<Gymregister> l = gymService.locateByCity(g.getCity());
				model.addAttribute("locGym", l);
				System.out.println("called list" + cityName);
				System.out.println("called");
				return "userHome";
			} else if (usname == null && gymuname != null) {
				System.out.println("Login as gym");
				return "gymDashboard";
			} else {
				session.invalidate();
				Gymregister g = new Gymregister();
				g.setCity(cityName);
				List<Gymregister> l = gymService.locateByCity(g.getCity());
				model.addAttribute("locGym", l);
				System.out.println("called list : " + cityName);
				System.out.println("called");
				return "home";
			}

		} else {
			HttpSession ses = req.getSession();
			ses.invalidate();
			Gymregister g = new Gymregister();
			g.setCity(cityName);
			List<Gymregister> l = gymService.locateByCity(g.getCity());

			model.addAttribute("locGym", l);
			System.out.println("called list : " + cityName);
			System.out.println("called");
			return "home";
		}

	}

	@RequestMapping("nearByUser")
	public String UserLoc(@RequestParam(required = false, value = "loc") String cityName, HttpServletRequest req,
			HttpServletResponse res, Model model) throws IOException {

		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();

		HttpSession session = req.getSession(false);
		if (session != null) {
			String usname = (String) session.getAttribute("unm");
			String gymuname = (String) session.getAttribute("gunm");
			if (usname != null && gymuname == null) {
				Gymregister g = new Gymregister();
				g.setCity(cityName);
				List<Gymregister> l = gymService.locateByCity(g.getCity());

				model.addAttribute("locGym", l);
				System.out.println("called list" + cityName);
				System.out.println("called");
				return "userHome";
			} else {
				pw.write("<div id='dis'  style='text-align:center;color:red;'><span>Login first !!!</span></div>");
				session.invalidate();
				return "hello";
			}
		} else {
			pw.write("<div id='dis'  style='text-align:center;color:red;'><span>Login first !!!</span></div>");
			return "hello";
		}

	}

	@RequestMapping("/loginForm")
	public String loginForm(Model model) {
		System.out.println("called");
		return "hello";
	}

	@RequestMapping("/gymData")
	public String gym(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
		res.setContentType("text/html");
		HttpSession session = req.getSession(false);
		if (session != null) {
			String usname = (String) session.getAttribute("unm");
			String gymuname = (String) session.getAttribute("gunm");
			if (usname != null && gymuname == null) {
				List<Gymregister> l = gymService.findAll();
				model.addAttribute("gymData", l);
				System.out.println("called list");
				return "gyms";
			} else {
				session.invalidate();
				return "hello";
			}
		} else {
			// session.invalidate();
			return "hello";
		}
	}

	@RequestMapping("/uDash")
	public String UserDash(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
		res.setContentType("text/html");

		HttpSession session = req.getSession(false);
		if (session != null) {
			String usname = (String) session.getAttribute("unm");
			String gymuname = (String) session.getAttribute("gunm");
			if (usname != null && gymuname == null) {
				List<Booking> ls = userService.getBookingID(userId);
				System.out.println("user id = " + userId);
				List<Gymregister> l = new ArrayList<Gymregister>();

				for (Booking booking : ls) {
					System.out.println("gym id = " + booking.getGid()+" "+booking.getMembership());
					Gymregister bgym = gymService.getBookedGym(booking.getGid());
					l.add(bgym);
					
					System.out.println("commed = " + bgym);
				}
				
				model.addAttribute("membership",ls);
				model.addAttribute("profileData", l);
				return "userDashboard";
			} else {
				session.invalidate();
				return "hello";
			}
		} else {
			return "hello";
		}
	}

	@RequestMapping("/highToLow")
	public String highToLow(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
		res.setContentType("text/html");

		HttpSession session = req.getSession(false);
		if (session != null) {
			String usname = (String) session.getAttribute("unm");
			String gymuname = (String) session.getAttribute("gunm");
			if (usname != null && gymuname == null) {

				List<Gymregister> l = gymService.findByhigh();

				model.addAttribute("gymData", l);
				return "gyms";
			} else {
				session.invalidate();
				return "home";
			}
		} else {

			return "home";
		}
	}

	@RequestMapping("/lowToHigh")
	public String lowtohigh(Model model) {

		List<Gymregister> l = gymService.findByLow();

		model.addAttribute("gymData", l);
		return "gyms";
	}

	@RequestMapping("/byCountry")
	public String byCountry(Model model) {

		List<Gymregister> l = gymService.findByCountry();

		model.addAttribute("gymData", l);
		return "gyms";
	}

	@RequestMapping("/byGymName")
	public String ByGymName(Model model) {
		List<Gymregister> l = gymService.findByGymName();

		model.addAttribute("gymData", l);
		return "gyms";
	}

	@RequestMapping("/gymCity")
	public String gymCity(@RequestParam("city") String city, Model model) {
		Gymregister g = new Gymregister();
		g.setCity(city);
		List<Gymregister> l = gymService.findByCity(g.getCity());

		model.addAttribute("gymData", l);
		System.out.println("called list");
		return "gyms";

	}//

	@RequestMapping("/gymreg")
	public String gymRegister(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		HttpSession session = req.getSession(false);

		if (session != null) {

			pw.write(
					"<div style='text-align:center;color:red;background-color:#989898;font-family: monospace;'><span id='dis'>Logout first !!!</span></div>");

			return "gym";
		} else {

			return "gymRegister";
		}

	}

	@RequestMapping("/register")
	public String register(Model model) {
		System.out.println("called register");
		return "register";
	}

	@RequestMapping(path = "/processForm", method = RequestMethod.POST)
	public String form(@ModelAttribute User user, HttpServletRequest req, HttpServletResponse res, Model model)
			throws IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();

		userService.saveUser(user);

		pw.write(
				"<div style='text-align:center;color:red;background-color:#989898;font-family: monospace;'><span id='dis'>Registered successfully!</span></div>");
		return "hello";
	}

	// ---------------------------------------booking--------------------------------//

	@RequestMapping(path = "/{id}")
	public String gymBooking(@PathVariable(value = "id") int theID, HttpServletRequest req, HttpServletResponse res,
			Model model) throws IOException, ServletException {
		HttpSession session = req.getSession(false);

		bookId = theID;
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();

		if (userId == 0 || session == null) {
			if (session != null) {
				session.invalidate();
				pw.write(
						"<div id='dis'  style='text-align:center;color:red;'><span>Please login as customer !!!</span></div>");

				return "hello";
			} else {
				pw.write(
						"<div id='dis'  style='text-align:center;color:red;'><span>Please login as customer !!!</span></div>");

				return "hello";
			}

		} else {
			String usname = (String) session.getAttribute("unm");
			if (usname != null) {
				List<Gymregister> l = gymService.getBookingGym(theID);
				List<Instructor> ins =instructorService.getInstructor(theID);
				System.out.println(l);
				System.out.println(ins+" "+theID);
				model.addAttribute("BookingGym", l);
				model.addAttribute("insdetail", ins);
				return "gymDetail";
			} else {
				pw.write(
						"<div id='dis'  style='text-align:center;color:red;'><span>Please login as customer !!!</span></div>");
				return "hello";
			}
		}
	}

	
	@RequestMapping("user")
	public String exp(@RequestParam(required = false, value = "membership") String membership, HttpServletRequest req, HttpServletResponse res,
			Model model) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		if (membership!=null) {
			bookingService.bookgym(bookId, userId,membership);
			System.out.println("new id = " + bookId+" "+membership);
			return "bookingConfirmation";
		} else {
			List<Gymregister> l = gymService.getBookingGym(bookId);
			List<Instructor> ins =instructorService.getInstructor(bookId);
			System.out.println(l);
			System.out.println(ins+" "+bookId);
			model.addAttribute("BookingGym", l);
			model.addAttribute("insdetail", ins);
			pw.write(
					"<div id='dis'  style='text-align:center;color:red;'><span>Please Choose Membership  </span></div>");
			return "gymDetail";
		}
		
	}

	// cancel booking

	@RequestMapping("cancel")
	public String CancelBooking(@RequestParam Integer id, Model model) {
		List<Gymregister> l = gymService.getBookingGym(id);
		System.out.println(l);
		model.addAttribute("CancelGym", l);
		System.out.println("cancel id = " + id);
		return "cancel";
	}

	@RequestMapping(path = "cancelConfirm")
	public String gymBooking(@RequestParam Integer id, HttpServletRequest req, HttpServletResponse res, Model model)
			throws IOException, ServletException {

		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();

		HttpSession session = req.getSession(false);

		if (session != null) {
			String usname = (String) session.getAttribute("unm");
			if (usname != null) {
				int a = bookingService.cancelBooking(userId, id);
				if (a == 1) {
					pw.write(
							"<div id='dis'  style='text-align:center;color:red;'><span>You have successfully canceled your booking !!!</span></div>");
					List<Booking> ls = userService.getBookingID(userId);
					System.out.println("user id = " + userId);
					List<Gymregister> l = new ArrayList<Gymregister>();
					for (Booking booking : ls) {
						System.out.println("gym id = " + booking.getGid());
						Gymregister bgym = gymService.getBookedGym(booking.getGid());
						l.add(bgym);
						System.out.println("commed = " + bgym);
					}

					model.addAttribute("profileData", l);

					return "userDashboard";
				} else {
					pw.write("<div id='dis'  style='text-align:center;color:red;'><span>Try again!!!</span></div>");

					return "userDashboard";
				}

			} else {
				pw.write(
						"<div id='dis'  style='text-align:center;color:red;'><span>You have successfully canceled your booking !!!</span></div>");
				return "hello";
			}
		} else {
			pw.write(
					"<div id='dis'  style='text-align:center;color:red;'><span>You have successfully canceled your booking !!!</span></div>");
			return "hello";
		}
	}

	// -----------------------------------------------------------------------------//

	@RequestMapping("/logUser")
	public String logUser(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {

		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();

		HttpSession session = req.getSession(false);
		if (session != null) {
			String usname = (String) session.getAttribute("unm");
			if (usname != null) {

				pw.write("<div id='dis'  style='text-align:center;color:red;'><span>You are login as Customer...!!!!!!!"
						+ usname + "</span></div>");
				System.out.println("you are already logged in as customer");
				User udetail = userService.getLoginUser(userId);
				model.addAttribute("user", udetail);
				return "userHome";
			} else {
				return "home";
			}

		} else {
			System.out.println("not logrd in");
			return "home";
		}

	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String name, @RequestParam("password") String password,
			HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {

		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();

		HttpSession session = req.getSession(false);

		if (session != null) {
			String usname = (String) session.getAttribute("unm");
			if (usname != null) {

				System.out.println("you are already logged in !!!!!!");
				return "userHome";
			} else {
				return "hello";
			}

		} else {
			if (name.length() < 1 || password.length() < 1) {
				pw.write(
						"<div id='dis'  style='text-align:center;color:red;'><span>Enter correct username or password !!!</span></div>");
				return "hello";
			} else {

				User u = new User();
				u.setUsername(name);
				u.setPassword(password);
				boolean check = gymService.userLogin(u.getUsername(), u.getPassword());

				System.out.println("check = " + check);
				if (check) {
					userId = gymService.getUserId(u.getUsername(), u.getPassword());
					u.setId(userId);
					User udetail = userService.getLoginUser(userId);

					session = req.getSession();
					session.setAttribute("unm", u.getUsername());
					session.setAttribute("pwd", u.getPassword());
					session.setAttribute("id", userId);
					model.addAttribute("user", udetail);
					return "userHome";
				} else {
					pw.write(
							"<div id='dis'  style='text-align:center;color:red;'><span>Enter correct username or password !!!</span></div>");
					return "hello";
				}
			}
		}
	}

//---------------------logout--------------------------//

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("successfully logged out");
		pw.write("<div id='dis'  style='text-align:center;color:red;'><span>Successfully logged out !!!</span></div>");
		return "home";

	}

	// -------------------------------------GymController------------------------------------------//
	String username;
	String pass;

	@RequestMapping("gymRegister")
	public String gymReg(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
		System.out.println("gym reg called");
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		HttpSession session = req.getSession(false);

		if (session != null) {
			String usname = (String) session.getAttribute("unm");
			String gsname = (String) session.getAttribute("gunm");
			if (usname != null) {

				pw.write("You are login as Customer...!!!!!!!");
				System.out.println("you are already logged in as customer");
				return "userHome";
			} else if (gsname != null) {

				return "gymDashboard";
			} else {
				session.invalidate();
				return "home";
			}
		} else {
			System.out.println("gym register called");
			return "gymRegister";
		}

	}

	@RequestMapping(path = "/GymRegistrationProcessForm", method = RequestMethod.POST)
	public String gymForm(@ModelAttribute Gymregister theGymRegister, HttpServletRequest req, HttpServletResponse res,
			Model model) throws IOException {
		System.out.println("gym reg called");
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		HttpSession session = req.getSession(false);

		if (session != null) {
			String usname = (String) session.getAttribute("unm");
			String gsname = (String) session.getAttribute("gunm");
			if (usname != null) {

				pw.write("You are login as Customer...!!!!!!!");
				System.out.println("you are already logged in as customer");
				return "userHome";
			} else if (gsname != null) {

				return "gymDashboard";
			} else {
				session.invalidate();
				return "home";
			}
		} else {

			gymService.saveGym(theGymRegister);

			return "home";
		}

	}

	@RequestMapping(path = "/gymLogin", method = RequestMethod.POST)
	public String gymlogin(@RequestParam("uname") String uname, @RequestParam("pwd") String password,
			HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
		System.out.println("called");
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		HttpSession session = req.getSession(false);

		if (session != null) {
			String usname = (String) session.getAttribute("unm");
			String gsname = (String) session.getAttribute("gunm");
			if (usname != null) {

				pw.write("You are login as Customer...!!!!!!!");
				System.out.println("you are already logged in as customer");
				return "userHome";
			} else if (gsname != null) {
				int id = (int) session.getAttribute("gymLoginId");

				List<Gymregister> l = gymService.getGym(username, pass);
				List<Instructor> ilist = instructorService.getTrainer(id);
				System.out.println("gym det = " + l);
				model.addAttribute("LoginGymDetail", l);
				model.addAttribute("trainerdetail", ilist);
				return "gymDashboard";
			} else {
				session.invalidate();
				return "home";
			}

		} else {

			Gymregister g = new Gymregister();
			g.setUname(uname);
			g.setPwd(password);

			username = g.getUname();
			pass = g.getPwd();

			boolean check = gymService.gymLogin(g.getUname(), g.getPwd());
			System.out.println("check = " + check);
			if (check) {
				session = req.getSession();
				List<Gymregister> l = gymService.getGym(username, pass);

				session.setAttribute("gymLoginId", l.get(0).getId());
				int id = (int) session.getAttribute("gymLoginId");
				List<Instructor> ilist = instructorService.getTrainer(id);

				model.addAttribute("LoginGymDetail", l);
				session.setAttribute("gunm", g.getUname());
				session.setAttribute("gpwd", g.getPwd());
				model.addAttribute("trainerdetail", ilist);
				return "gymDashboard";
			} else {
				pw.write(
						"<div id='dis'  style='text-align:center;color:red;'><span>Incorrect username and password!!</span></div>");
				return "home";
			}
		}

	}
	// --------------------------gymupdation--------------------------------///

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public String updateAddress(@ModelAttribute(binding = true) Gymregister gymUpdate, Model model) {

		System.out.println("add " + gymUpdate.getAddressline() + " cit " + gymUpdate.getCity() + " st "
				+ gymUpdate.getState() + "co " + gymUpdate.getCountry() + "pin " + gymUpdate.getPincode());

		gymService.updateAddress(gymUpdate, username, pass);

		List<Gymregister> l = gymService.getGym(username, pass);

		System.out.println("gym det = " + l);
		model.addAttribute("LoginGymDetail", l);
		return "gymDashboard";
	}

	@RequestMapping(path = "/updateContact", method = RequestMethod.POST)
	public String updateContact(@RequestParam(required = false, value = "contact") String contact, Model model) {

		gymService.updateContact(contact, username, pass);

		List<Gymregister> l = gymService.getGym(username, pass);

		System.out.println("gym det = " + l);
		model.addAttribute("LoginGymDetail", l);
		return "gymDashboard";
	}

	@RequestMapping(path = "/updateprice", method = RequestMethod.POST)
	public String updatePrice(@RequestParam(required = false, value = "price") String price,
			@RequestParam(required = false, value = "offer") String offer,@RequestParam(required = false, value = "offermonth") String offermonth,@RequestParam(required = false, value = "offeryear") String offeryear, Model model) {
		System.out.println("price called");

		System.out.println("pruce " + price);
		double p;
		if (price.isEmpty()) {
			p = 0;
			gymService.updatePrice(p, offer + "%",offermonth+ "%",offeryear+ "%", username, pass);

			List<Gymregister> l = gymService.getGym(username, pass);
			System.out.println("gym det = " + l);
			model.addAttribute("LoginGymDetail", l);
			return "gymDashboard";
		} else {
			p = Double.parseDouble(price);
			gymService.updatePrice(p, offer + "%",offermonth+ "%",offeryear+ "%", username, pass);

			List<Gymregister> l = gymService.getGym(username, pass);
			System.out.println("gym det = " + l);
			model.addAttribute("LoginGymDetail", l);
			return "gymDashboard";
		}

	}

	@RequestMapping(path = "/updateInstructor", method = RequestMethod.POST)
	public String updateInstructor(@RequestParam(required = true, value = "trainername") String name,
			@RequestParam(required = false, value = "experience") String exp,
			@RequestParam(required = false, value = "gender") String gen, HttpServletRequest req,
			HttpServletResponse res, Model model) {

		HttpSession session = req.getSession(false);
			List<Gymregister> l = gymService.getGym(username, pass);
			int id = (int) session.getAttribute("gymLoginId");
			//List<Instructor> compare = instructorService.getTrainer(id);
			
			Instructor i = instructorService.getInstructor(name, exp, gen);
			
			if (i!=null) {
				List<Instructor> ilist = instructorService.getTrainer(id);		
				model.addAttribute("LoginGymDetail", l);
				model.addAttribute("trainerdetail", ilist);
				return "gymDashboard";
			} else {
				instructorService.UpdateIns(name, exp, gen, id);
				List<Instructor> ilist = instructorService.getTrainer(id);		
				model.addAttribute("LoginGymDetail", l);
				model.addAttribute("trainerdetail", ilist);
				return "gymDashboard";
			}
			
			
//			boolean b=false;
//			int count=0;
//			for (Instructor instructor : compare) {
//				if (instructor.getTrainername() != name && instructor.getExperience() != exp && instructor.getGender() != gen) {
//					b=false;
//					instructorService.UpdateIns(name, exp, gen, id);
//					System.out.println("false condition");
//				} else {
//					b=true;
//					count++;
//					System.out.println("breaked");
//				break;
//				
//				}
//				
//			}
//
//			if (b) {
//				
//				List<Instructor> ilist = instructorService.getTrainer(id);
//				System.out.println("not uodated");
//				System.out.println("gym det = " + "ok+");
//				model.addAttribute("LoginGymDetail", l);
//				model.addAttribute("trainerdetail", ilist);
//				return "gymDashboard";
//			} else {
//				System.out.println("updated");
//				//instructorService.UpdateIns(name, exp, gen, id);
//				List<Instructor> ilist = instructorService.getTrainer(id);
//				System.out.println("gym det = " + "ok+");
//				model.addAttribute("LoginGymDetail", l);
//				model.addAttribute("trainerdetail", ilist);
//				return "gymDashboard";
//			}
		
	}

	@RequestMapping("deleteTrainer")
	public String deleteTrainer(Integer id, HttpServletRequest req, HttpServletResponse res, Model model) {
		System.out.println("trainer id = " + id);
		instructorService.deleteTrainer(id);
		HttpSession session = req.getSession(false);
		int i = (int) session.getAttribute("gymLoginId");

		List<Gymregister> l = gymService.getGym(username, pass);
		List<Instructor> ilist = instructorService.getTrainer(i);
		System.out.println("gym det = " + l);
		model.addAttribute("LoginGymDetail", l);
		model.addAttribute("trainerdetail", ilist);
		return "gymDashboard";
	}

	@RequestMapping(path = "/updateAadhar", method = RequestMethod.POST)
	public String updateDetails(@RequestParam("aadhar") String adhar, Model model) {

		gymService.updateDetail(adhar, username, pass);

		List<Gymregister> l = gymService.getGym(username, pass);

		System.out.println("gym det = " + l);
		model.addAttribute("LoginGymDetail", l);
		return "gymDashboard";
	}

	@RequestMapping(path = "/updateGymName", method = RequestMethod.POST)
	public String updateGymNAme(@RequestParam(required = false, value = "gymname") String name, Model model) {

		gymService.updateGymName(name, username, pass);

		List<Gymregister> l = gymService.getGym(username, pass);

		System.out.println("gym det = " + l);
		model.addAttribute("LoginGymDetail", l);
		return "gymDashboard";
	}

	/////////// --------------------------gym updation
	/////////// end--------------------------------///

	// ----------------booking details------------//

	@RequestMapping("bookingDetails")
	public String bookingDet(Model model) {

		int id = gymService.getGYmID(username, pass);

		System.out.println("gym book id s= " + id);

		List<Booking> lid = gymService.getGymIDList(id);

		List<User> l = new ArrayList<User>();
		for (Booking b : lid) {
			System.out.println("iterated");
			User userList = userService.getUserList(b.getUid());
			l.add(userList);
			System.out.println(" comm = = " + userList);
		}

		model.addAttribute("BookingUsers", l);
		return "BookingDetail";
	}

	// ----------end---------------------//
}// class
