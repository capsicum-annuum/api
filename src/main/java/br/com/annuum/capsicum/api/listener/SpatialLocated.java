package br.com.annuum.capsicum.api.listener;

import br.com.annuum.capsicum.api.domain.SpatialLocation;
import com.vividsolutions.jts.geom.Point;

public interface SpatialLocated {

  SpatialLocation getSpatialLocation();

  SpatialLocated setSpatialLocation(SpatialLocation location);

  Point getPoint();

  SpatialLocated setPoint(Point location);

}
