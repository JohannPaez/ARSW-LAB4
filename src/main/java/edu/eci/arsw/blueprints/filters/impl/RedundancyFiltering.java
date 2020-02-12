package edu.eci.arsw.blueprints.filters.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import edu.eci.arsw.blueprints.filters.Filter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Component
public class RedundancyFiltering implements Filter {

	@Override
	public Blueprint filter(Blueprint blue) {
		Set<Point> points = new HashSet<>();
		for (Point p: blue.getPoints()) {
			points.add(p);
		}
		int lon = points.size();
		Point[] filters = new Point[lon];
		int aux = 0;
		for (Point p: points) {
			filters[aux] = p;
			aux++;
		}
		return new Blueprint(blue.getAuthor(), blue.getName(), filters);
	}

}
