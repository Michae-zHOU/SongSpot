export function createRequestTypes(type) {
  return {
    REQUEST: `${type}_REQUEST`,
    SUCCESS: `${type}_SUCCESS`,
    FAILURE: `${type}_FAILURE`,
  };
}

export function action(type, payload) {
  return payload ? { type, ...payload } : { type };
}
