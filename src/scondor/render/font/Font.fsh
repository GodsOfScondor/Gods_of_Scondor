#version 330

in vec2 coords;

out vec4 out_color;

uniform sampler2D font;
uniform vec3 color;
uniform float transparency;

const float width = 0.5;
const float edge = 0.1;

void main(void){
	
	float distance = 1.0 - texture(font, coords).a;
	float alpha = 1.0 - smoothstep(width, width + edge, distance);
	
	out_color = vec4(color.xyz, min(transparency, alpha));
	
}