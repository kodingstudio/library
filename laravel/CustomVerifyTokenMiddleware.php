<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\Response;

class CustomVerifyTokenMiddleware
{
    /**
     * Handle an incoming request.
     *
     * @param \Closure(\Illuminate\Http\Request): (\Symfony\Component\HttpFoundation\Response)  $next
     */
    public function handle(Request $request, Closure $next): Response
    {
        $token = $request->bearerToken();

        if (!$token) {
            return response()->json(['message' => 'Unauthorized. Missing token.'], ERROR_CODE_BAD_REQUEST);
        }

        // Manually validate the token (replace with your own validation logic)
        $tokenObject = $this->validateToken($token);
        if (!($tokenObject !== null)) {
            return response()->json(['message' => 'Unauthorized. Invalid token.'], ERROR_CODE_UN_AUTHORIZED);
        }

        $request->merge(['user_id' => $tokenObject->tokenable_id]);
        $request->merge(['token_type' => $tokenObject->tokenable_type]);

        return $next($request);
    }

    private function validateToken($token)
    {
        return \Laravel\Sanctum\PersonalAccessToken::findToken($token);
    }

}
