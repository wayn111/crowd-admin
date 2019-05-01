package com.wayn.controller.index;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wayn.commom.base.BaseControlller;

@Controller
public class IndexController extends BaseControlller {

	@GetMapping("/")
	public String index(Model model) {
		return redirectTo("/main");
	}

	@ResponseBody
	@GetMapping("/test")
	public List<TestObj> test(Model model) {
		List<TestObj> list = new ArrayList<IndexController.TestObj>();
		for (int i = 0; i < 10; i++) {
			TestObj obj = new TestObj();
			obj.setId(UUID.randomUUID().toString().substring(0, 5));
			obj.setName("name/" + i);
			obj.setPrice(1d);
			list.add(obj);
		}
		return list;
	}

	class TestObj {
		private String id;
		private String name;
		private double price;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

	}
}
