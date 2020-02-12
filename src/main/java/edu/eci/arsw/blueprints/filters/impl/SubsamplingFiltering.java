package edu.eci.arsw.blueprints.filters.impl;



import java.util.List;

import org.springframework.stereotype.Component;

import edu.eci.arsw.blueprints.filters.Filter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Component
public class SubsamplingFiltering implements Filter {

	@Override
	public Blueprint filter(Blueprint blue) {
		List<Point> points = blue.getPoints();
		int lon = points.size() % 2 + points.size() / 2;
		Point[] filters = new Point[lon];
		for (int i = 0; i < points.size(); i += 2) {
			filters[i/2] = points.get(i); 
		}
		Blueprint blueTwo = new Blueprint(blue.getAuthor(), blue.getName(), filters);
		return blueTwo;
	}

}
