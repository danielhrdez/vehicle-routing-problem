package vrp.algorithm.util;

import vrp.algorithm.constructsearch.localsearch.base.*;
import vrp.algorithm.constructsearch.localsearch.opt.*;
import vrp.algorithm.constructsearch.localsearch.reinsertion.*;
import vrp.algorithm.constructsearch.localsearch.swap.*;
import vrp.data.DataModel;
import vrp.solution.Routes;

/**
 * Class with utility functions.
 */
public class Functions {
  /**
   * Add the depot to all the vehicles.
   * @param routes The routes.
   * @param dataModel The data model.
   * @return The routes with the depot.
   */
  public static Routes addDepot(Routes routes, DataModel dataModel) {
    int numberOfVehicles = dataModel.getNumberOfVehicles();
    int depot = dataModel.getDepot();
    dataModel.setCustomer(depot);

    for (int i = 0; i < numberOfVehicles; i++) {
      try {
        routes.sumCost(dataModel.distance(routes.lastCustomerFromRoute(i), depot));
        routes.addCustomer(i, depot);
      } catch (Exception e) {
        routes.addCustomer(i, depot);
        routes.sumCost(dataModel.distance(routes.lastCustomerFromRoute(i), depot));
      }
    }

    return routes;
  }

  /**
   * Check if the route is full.
   * @param routes The routes.
   * @param route The route.
   * @return True if the route is full.
   */
  public static boolean full(Routes routes, int route, int maxCustomersByRoute) {
    return routes.getRouteSize(route) >= maxCustomersByRoute;
  }

  /**
   * Setter of the local search algorithm.
   * @param localSearchType The local search algorithm.
   * @return The local search algorithm.
   */
  public static LocalSearch setLocalSearchType(LocalSearchType localSearchType) {
    switch (localSearchType) {
      case REINSERTION_INTER: return new ReinsertionInterRoute();
      case REINSERTION_INTRA: return new ReinsertionIntraRoute();
      case SWAP_INTER: return new SwapInterRoute();
      case SWAP_INTRA: return new SwapIntraRoute();
      case TWO_OPT: return new TwoOpt();
      default: return new ReinsertionInterRoute();
    }
  }
}
